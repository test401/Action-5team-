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

import action.business.domain.reply.AuctionBoardReply;
import action.business.service.reply.AuctionBoardReplyDao;

public class AuctionBoardReplyDaoImpl implements AuctionBoardReplyDao {

	private DataSource dataSource;

	public AuctionBoardReplyDaoImpl(){
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
	 * @see action.business.service.reply.AuctionBoardReplyDao#selectReplyList(int)
	 */
	@Override
	public List<AuctionBoardReply> selectReplyList(int boardNum) {	

		String query = "SELECT replyNum, writer, replyContent, replyStep FROM AuctionBoardReply ORDER BY masterNum, replyNum WHERE auctionBoardNum = ?";
		
		AuctionBoardReply reply = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AuctionBoardReply> replyList = new ArrayList<AuctionBoardReply>();

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);			
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				reply = new AuctionBoardReply(rs.getInt("replyNum"),
						rs.getString("writer"),
						rs.getString("replyContent"),						
						rs.getInt("replyStep"));
				replyList.add(reply);
			}

		}catch(SQLException se){
			System.err.println("AuctionBoardReplyDaoImpl selectReplyList() Error :" + se.getMessage());
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}

		return replyList;
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.AuctionBoardReplyDao#selectReplyCount(int)
	 */
	@Override
	public int selectReplyCount(int boardNum) {
		int boardCount = 0;

		// 4. SELECT 문에 생성된 WHERE 절을 붙인다.
		String query = "SELECT count(replynum) FROM AuctionBoardReply WHERE auctionBoardNum = ?";

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
			System.err.println("AuctionBoardReplyDaoImpl selectReplyCount() Error :" + se.getMessage());
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}
		return boardCount;
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.AuctionBoardReplyDao#selectReply(int)
	 */
	@Override
	public AuctionBoardReply selectReply(int replynum) {
		AuctionBoardReply reply = null;

		String query = "SELECT * FROM AuctionBoardReply WHERE replynum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replynum);
			rs = pstmt.executeQuery();

			if(rs.next()){
				reply = new AuctionBoardReply(rs.getInt("replynum"),
						rs.getString("writer"),
						rs.getInt("masterNum"),
						rs.getInt("replyOrder"),
						rs.getInt("replyStep"),
						rs.getString("replyContent"),
						rs.getInt("auctionBoardNum"),
						rs.getString("replyPwd"),
						rs.getInt("isMember"));
			}
		}catch(SQLException se){
			System.err.println("AuctionBoardReplyDaoImpl selectReply() Error :" + se.getMessage());
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
	 * @see action.business.service.reply.AuctionBoardReplyDao#isReplyExists(int)
	 */
	@Override
	public boolean isReplyExists(int replyNum) {
		boolean isExist = false;

		String query="SELECT * FROM AuctionBoardReply WHERE replyNum = ?";

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
			System.err.println("AuctionBoardReplyDaoImpl isReplyExists() Error :" + se.getMessage());
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
	 * @see action.business.service.reply.AuctionBoardReplyDao#insertReply(action.business.domain.reply.AuctionBoardReply)
	 */
	@Override
	public void insertReply(AuctionBoardReply reply) {
		String query = "INSERT INTO AuctionBoardReply (replyNum, auctionBoardNum, writer, "
				+ "replyContent, masterNum, replyPwd VALUES (board_num_seq.NEXTVAL, ?, ?, ?, board_num_seq.CURRVAL, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, reply.getAuctionBoardNum());
			pstmt.setString(2, reply.getWriter());
			pstmt.setString(3, reply.getReplyContent());
			pstmt.setString(4, reply.getReplyPwd());

			pstmt.executeUpdate();

		}catch(SQLException se){
			System.err.println("AuctionBoardReplyDaoImpl insertReply() Error :" + se.getMessage());
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
	 * @see action.business.service.reply.AuctionBoardReplyDao#InsertReplyOnReply(action.business.domain.reply.AuctionBoardReply)
	 */
	public void InsertReplyOnReply(AuctionBoardReply reply){
		String query = "UPDATE AuctionBoardReply SET replyOrder = replyOrder + 1 "
				+ "WHERE masterNum = ? AND replyOrder > ?";
		
		String query2 = "INSERT INTO AuctionBoardReply (replyNum, auctionBoardNum, writer, "
				+ "replyContent, masterNum, replyPwd, replyOrder, replyStep) VALUES "
				+ "(board_num_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

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

			pstmt.setInt(1, reply.getAuctionBoardNum());
			pstmt.setString(2, reply.getWriter());
			pstmt.setString(3, reply.getReplyContent());
			pstmt.setInt(4, reply.getMasterNum());
			pstmt.setString(5, reply.getReplyPwd());
			pstmt.setInt(6, reply.getReplyOrder() + 1); // 현재 답글 다음에 위치시켜야 하므로 reply_order + 1
			pstmt.setInt(7, reply.getReplyStep() + 1); // 현재 답글에 대한 답글이므로 reply_step + 1

			pstmt.executeUpdate();

		}catch(SQLException se){
			System.err.println("AuctionBoardReplyDaoImpl InsertReplyOnReply() Error :" + se.getMessage());
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
	 * @see action.business.service.reply.AuctionBoardReplyDao#updateReply(action.business.domain.reply.AuctionBoardReply)
	 */
	@Override
	public void updateReply(AuctionBoardReply reply) {
		String query="UPDATE AuctionBoardReply SET replyContent=? WHERE replyNum=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getReplyContent());			
			pstmt.setInt(2, reply.getReplyNum());			

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("AuctionBoardReplyDaoImpl updateReply() Error :" + se.getMessage());
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
	 * @see action.business.service.reply.AuctionBoardReplyDao#deleteReply(int)
	 */
	@Override
	public void deleteReply(int replynum) {
		String query=" DELETE FROM AuctionBoardReply WHERE replyNum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, replynum);

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("AuctionBoardReplyDaoImpl deleteReply() Error :" + se.getMessage());
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
