package action.dataaccess.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import action.business.domain.member.Member;
import action.business.service.member.MemberDao;

public class MemberDaoImpl implements MemberDao {
	/** 멤버 DB 새로 만들 필요 있음 */
	private DataSource dataSource;	

	/*
	 * 1. Driver 클래스(com.mysql.jdbc.Driver)를 Class의 forName 메서드를 사용하여 로딩한다.
	 */
	public MemberDaoImpl() {
		try {
			//Class.forName("oracle.jdbc.OracleDriver");
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/actionDB");
		} catch(NamingException ne) {
			System.err.println("JNDI error occured");
			ne.printStackTrace(System.err);
			throw new RuntimeException("JNDI error occured. " + ne.getMessage());
		}
	}

	/*
	 * DriverManager의 getConnection() 메소드를 통해 Connection을 만든다.
	 */
	private Connection obtainConnection() throws SQLException {
		// return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		return dataSource.getConnection();
	}

	@Override
	public void insertMember(Member member) {
		String query = "INSERT INTO AuctionMember VALUES (?, ?, ?, ?, ?, 0, 0)";

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = obtainConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getTel());			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			System.err.println("MemberDAOImpl insertMember() Error :" + se.getMessage());
			se.printStackTrace(System.err);
			//throw new RuntimeException("A database error occurred. " + se.getMessage());

		} finally {
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (connection != null) connection.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
		}

	}

	@Override
	public Member selectMember(String id) {
		Member member = null;

		String query = "SELECT * FROM AuctionMember WHERE memberID = ?";        		

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = obtainConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();    

			if (rs.next()) {
				member = new Member(rs.getString("memberID"), 
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("tel"),
						rs.getInt("warnCount"),
						rs.getInt("isAdmin"));
			}

		} catch(SQLException se) {
			System.err.println("MemberDAOImpl selectMember() Error :" + se.getMessage());
			se.printStackTrace(System.err);        	
			//throw new RuntimeException("A database error occurred. " + se.getMessage());

		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try {
				if (connection != null) connection.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
		}

		return member;
	}

	@Override
	public void updateMember(Member member) {
		String query = "UPDATE AuctionMember SET password = ?, Name = ?, address = ?, Tel = ? "
				+ "WHERE memberID = ?";

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = obtainConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getTel());
			pstmt.setString(5, member.getMemberID());

			pstmt.executeUpdate();

		} catch(SQLException se) {
			System.err.println("MemberDAOImpl updateMember() Error :" + se.getMessage());
			se.printStackTrace(System.err);    
			//throw new RuntimeException("A database error occurred. " + se.getMessage());

		} finally {
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (connection != null) connection.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
		}

	}

	@Override
	public void deleteMember(Member member) {
		String query = "DELETE FROM AuctionMember WHERE memberID = ?";

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = obtainConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, member.getMemberID());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			System.err.println("MemberDAOImpl deleteMember() Error :" + se.getMessage());
			se.printStackTrace(System.err);
			//throw new RuntimeException("A database error occurred. " + se.getMessage());

		} finally {
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (connection != null) connection.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
		}	

	}

	@Override
	public Member checkMember(String memberID, String password) {
		Member member = new Member(memberID, password);

		String query = "SELECT Password, Name, address, Tel, warnCount, isAdmin FROM AuctionMember WHERE memberID = ?";

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = obtainConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String pw = rs.getString("password");
				if (pw.equals(password)) {
					member.setName(rs.getString("Name"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
					member.setWarnCount(rs.getInt("warnCount"));
					member.setIsAdmin(rs.getInt("isAdmin"));					
					member.setLoginCheck(Member.VALID_MEMBER);
				} else {
					member.setLoginCheck(Member.INVALID_PASSWORD);
				}
			} else {
				member.setLoginCheck(Member.INVALID_ID);
			}

		} catch(SQLException se) {
			System.err.println("MemberDAOImpl checkMember() Error :" + se.getMessage());
			se.printStackTrace(System.err);
			//throw new RuntimeException("A database error occurred. " + se.getMessage());

		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
			try { if (connection != null) connection.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
		}

		return member;
	}

	@Override
	public Member[] selectAllMembers() {
		String query = "SELECT * FROM AuctionMember";                

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Member> temp = new ArrayList<Member>();
		Member member = null;        

		try {
			connection = obtainConnection();
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member(rs.getString("memberID"), 
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("tel"),
						rs.getInt("warnCount"),
						rs.getInt("isAdmin"));
				temp.add(member);            	
			}           

		} catch(SQLException se) {
			System.err.println("MemberDAOImpl selectAllMembers() Error :" + se.getMessage());
			se.printStackTrace(System.err);  
			//throw new RuntimeException("A database error occurred. " + se.getMessage());

		} finally {
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (connection != null) connection.close(); } catch(SQLException ex){ ex.printStackTrace(System.err); }
		}

		return temp.toArray(new Member[0]);
	}

	@Override
	public boolean memberIDExists(String memberID) {
		boolean result = false;	

		String query = "SELECT * FROM AuctionMember WHERE memberID = ?";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = obtainConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
									
			if (rs.next()) {
				result = true;
			}
			// result = rs.next(); //위 내용을 간단히 하면 이렇게 씀 
			
			
		} catch(SQLException se) {
			System.err.println("MemberDAOImpl memberIDExists() Error :" + se.getMessage());
			se.printStackTrace(System.err);        	
			//throw new RuntimeException("A database error occurred. " + se.getMessage());

		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try {
				if (connection != null) connection.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
		}	
		return result;
	}

}
