package action.dataaccess.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import action.business.domain.reply.FreeBoardReply;
import action.business.service.reply.FreeBoardReplyDao;

public class FreeBoardReplyDaoImpl implements FreeBoardReplyDao {

	private DataSource dataSource;

	public FreeBoardReplyDaoImpl(){
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/actionDB");
		} catch(NamingException ne) {
			System.err.println("JNDI error occured");
			ne.printStackTrace(System.err);
			throw new RuntimeException("JNDI error occured. " + ne.getMessage());
		}
	}

	private Connection obtainConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyDao#selectReplyList(int)
	 */
	@Override
	public List<FreeBoardReply> selectReplyList(int boardNum) {	

		String query = "SELECT replyNum, memberID, replyContent, replyStep FROM FreeBoardReply ORDER BY masterNum, replyNum WHERE freeBoardNum = ?";
		
		FreeBoardReply reply = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FreeBoardReply> replyList = new ArrayList<FreeBoardReply>();

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);			
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				reply = new FreeBoardReply(rs.getInt("replyNum"),
						rs.getString("memberID"),
						rs.getString("replyContent"),
						rs.getInt("replyStep"));
				replyList.add(reply);
			}

		}catch(SQLException se){
			System.err.println("FreeBoardReplyDaoImpl selectReplyList() Error :" + se.getMessage());
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}

		return replyList;
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyDao#selectReplyCount(int)
	 */
	@Override
	public int selectReplyCount(int boardNum) {
		int boardCount = 0;

		// 4. SELECT 문에 생성된 WHERE 절을 붙인다.
		String query = "SELECT count(replynum) FROM FreeBoardReply WHERE freeBoardNum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();

			//boardCount = rs.getInt("count(num)");			

			if(rs.next()){
				boardCount = rs.getInt(1);
			}

		}catch(SQLException se){
			System.err.println("FreeBoardReplyDaoImpl selectReplyCount() Error :" + se.getMessage());
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}
		return boardCount;
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyDao#selectReply(int)
	 */
	@Override
	public FreeBoardReply selectReply(int replynum) {
		FreeBoardReply reply = null;

		String query = "SELECT * FROM FreeBoardReply WHERE replynum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replynum);
			rs = pstmt.executeQuery();

			if(rs.next()){
				reply = new FreeBoardReply(rs.getInt("replynum"),
						rs.getInt("freeBoardNum"),
						rs.getString("writer"),
						rs.getString("replyContent"),
						rs.getInt("masterNum"),
						rs.getInt("replyOrder"),
						rs.getInt("replyStep"));
			}
		}catch(SQLException se){
			System.err.println("FreeBoardReplyDaoImpl selectReply() Error :" + se.getMessage());
			se.printStackTrace(System.err);
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}

		return reply;
	}

	
	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyDao#isReplyExists(int)
	 */
	@Override
	public boolean isReplyExists(int replyNum) {
		boolean isExist = false;

		String query="SELECT * FROM FreeBoardReply WHERE replyNum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNum);
			rs = pstmt.executeQuery();

			isExist = rs.next();


		}catch(SQLException se){
			System.err.println("FreeBoardReplyDaoImpl isReplyExists() Error :" + se.getMessage());
			se.printStackTrace(System.err);
		}finally{
			try { 
				if (rs != null) rs.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (conn != null) conn.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }

		}

		return isExist;
	}


	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyDao#insertReply(action.business.domain.reply.FreeBoardReply)
	 */
	@Override
	public void insertReply(FreeBoardReply reply) {
		String query = "INSERT INTO FreeBoardReply (replyNum, freeBoardNum, memberID, "
				+ "replyContent, masterNum "
				+ "VALUES (board_num_seq.NEXTVAL, ?, ?, ?, board_num_seq.CURRVAL)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, reply.getFreeBoardNum());
			pstmt.setString(2, reply.getMemberID());
			pstmt.setString(3, reply.getReplyContent());

			pstmt.executeUpdate();

		}catch(SQLException se){
			System.err.println("FreeBoardReplyDaoImpl insertReply() Error :" + se.getMessage());
			se.printStackTrace(System.err);
		}finally{
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (conn != null) conn.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
		}

	}	
	
	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyDao#InsertReplyOnReply(action.business.domain.reply.FreeBoardReply)
	 */
	public void InsertReplyOnReply(FreeBoardReply reply){
		String query = "UPDATE FreeBoardReply SET replyOrder = replyOrder + 1 "
				+ "WHERE masterNum = ? AND replyOrder > ?";
		
		String query2 = "INSERT INTO FreeBoardReply (replyNum, freeBoardNum, writer, "
				+ "replyContent, masterNum, replyOrder, replyStep) VALUES "
				+ "(board_num_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, reply.getMasterNum());
			pstmt.setInt(2, reply.getReplyOrder());
		
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement(query2);

			pstmt.setInt(1, reply.getFreeBoardNum());
			pstmt.setString(2, reply.getMemberID());
			pstmt.setString(3, reply.getReplyContent());
			pstmt.setInt(4, reply.getMasterNum());			
			pstmt.setInt(5, reply.getReplyOrder() + 1); // 현재 답글 다음에 위치시켜야 하므로 reply_order + 1
			pstmt.setInt(6, reply.getReplyStep() + 1); // 현재 답글에 대한 답글이므로 reply_step + 1

			pstmt.executeUpdate();

		}catch(SQLException se){
			System.err.println("FreeBoardReplyDaoImpl InsertReplyOnReply() Error :" + se.getMessage());
			se.printStackTrace(System.err);
		}finally{
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (conn != null) conn.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
		}
	}
	

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyDao#updateReply(action.business.domain.reply.FreeBoardReply)
	 */
	@Override
	public void updateReply(FreeBoardReply reply) {
		String query="UPDATE FreeBoardReply SET replyContent=? WHERE replyNum=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getReplyContent());			
			pstmt.setInt(2, reply.getReplyNum());			

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("FreeBoardReplyDaoImpl updateReply() Error :" + se.getMessage());
			se.printStackTrace(System.err);
		}finally{
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (conn != null) conn.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
		}

	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyDao#deleteReply(int)
	 */
	@Override
	public void deleteReply(int replynum) {
		String query=" DELETE FROM FreeBoardReply WHERE replyNum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, replynum);

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("FreeBoardReplyDaoImpl deleteReply() Error :" + se.getMessage());
			se.printStackTrace(System.err);
		}finally{
			try { 
				if (pstmt != null) pstmt.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
			try { 
				if (conn != null) conn.close(); 
			} catch (SQLException se) { se.printStackTrace(System.err); }
		}

	}	
	
}
