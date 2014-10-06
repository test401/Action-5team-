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

import action.business.domain.reply.QnABoardReply;
import action.business.service.reply.QnABoardReplyDao;

public class QnABoardReplyDaoImpl implements QnABoardReplyDao {

	private DataSource dataSource;

	public QnABoardReplyDaoImpl(){
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

	/**
	 * 해당 게시글 내의 모든 댓글 목록을 조회한다.
	 * 
	 * @return 검색된 댓글 목록을 담고 있는 List 객체
	 */
	@Override
	public List<QnABoardReply> selectReplyList(int boardNum) {	

		String query = "SELECT replyNum, memberID, replyContent, replyStep FROM QnABoardReply ORDER BY masterNum, replyNum WHERE QnABoardNum = ?";
		
		QnABoardReply reply = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnABoardReply> replyList = new ArrayList<QnABoardReply>();

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);			
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				reply = new QnABoardReply(rs.getInt("replyNum"),
						rs.getString("memberID"),
						rs.getString("replyContent"),
						rs.getInt("replyStep"));
				replyList.add(reply);
			}

		}catch(SQLException se){
			System.err.println("QnABoardReplyDaoImpl selectReplyList() Error :" + se.getMessage());
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}

		return replyList;
	}

	/**
	 * 해당 게시글 내 모든 댓글 개수를 조회한다.
	 * 
	 * @return 검색된 모든 게시글의 개수
	 */
	@Override
	public int selectReplyCount(int boardNum) {
		int boardCount = 0;

		// 4. SELECT 문에 생성된 WHERE 절을 붙인다.
		String query = "SELECT count(replynum) FROM QnABoardReply WHERE QnABoardNum = ?";

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
			System.err.println("QnABoardReplyDaoImpl selectReplyCount() Error :" + se.getMessage());
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}
		return boardCount;
	}

	/**
	 * 지정된 번호에 해당하는 게시글을 검색한다.
	 * @param num 검색하고자 하는 게시글의 번호
	 * @return
	 */
	@Override
	public QnABoardReply selectReply(int replynum) {
		QnABoardReply reply = null;

		String query = "SELECT * FROM QnABoardReply WHERE replynum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replynum);
			rs = pstmt.executeQuery();

			if(rs.next()){
				reply = new QnABoardReply(rs.getInt("replynum"),
						rs.getString("memberID"),
						rs.getString("replyContent"),
						rs.getInt("masterNum"),
						rs.getInt("replyOrder"),						
						rs.getInt("replyStep"),
						rs.getInt("qnaBoardNum"));
			}
		}catch(SQLException se){
			System.err.println("QnABoardReplyDaoImpl selectReply() Error :" + se.getMessage());
			se.printStackTrace(System.err);
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}

		return reply;
	}

	
	/**
	 * 인수로 주어진 번호에 해당하는 게시글이 있는지 확인한다.
	 * 
	 * @param num 존재여부를 확인하려는 게시글의 번호
	 * @return 해당하는 게시글이 존재하면 true, 존재하지 않으면 false
	 */
	@Override
	public boolean isReplyExists(int replyNum) {
		boolean isExist = false;

		String query="SELECT * FROM QnABoardReply WHERE replyNum = ?";

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
			System.err.println("QnABoardReplyDaoImpl isReplyExists() Error :" + se.getMessage());
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

	/**
	 * 인수로 주어진 Board 객체의 정보로 새로운 게시글을 등록한다.
	 * 
	 * @param board 등록할 게시글 정보를 담고 있는 Board 객체
	 */
	@Override
	public void insertReply(QnABoardReply reply) {
		String query = "INSERT INTO QnABoardReply (replyNum, qnaBoardNum, memberID, "
				+ "replyContent, masterNum "
				+ "VALUES (board_num_seq.NEXTVAL, ?, ?, ?, board_num_seq.CURRVAL)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, reply.getQnaBoardNum());
			pstmt.setString(2, reply.getMemberID());
			pstmt.setString(3, reply.getReplyContent());

			pstmt.executeUpdate();

		}catch(SQLException se){
			System.err.println("QnABoardReplyDaoImpl insertReply() Error :" + se.getMessage());
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
	
	/**
	 * 인수로 주어진 reply 객체의 정보로 새로운 답글을 등록한다.
	 * 
	 * @param reply 등록할 답글 정보를 담고 있는 reply 객체
	 */
	public void InsertReplyOnReply(QnABoardReply reply){
		String query = "UPDATE QnABoardReply SET replyOrder = replyOrder + 1 "
				+ "WHERE masterNum = ? AND replyOrder > ?";
		
		String query2 = "INSERT INTO QnABoardReply (replyNum, qnaBoardNum, writer, "
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

			pstmt.setInt(1, reply.getQnaBoardNum());
			pstmt.setString(2, reply.getMemberID());
			pstmt.setString(3, reply.getReplyContent());
			pstmt.setInt(4, reply.getMasterNum());			
			pstmt.setInt(5, reply.getReplyOrder() + 1); // 현재 답글 다음에 위치시켜야 하므로 reply_order + 1
			pstmt.setInt(6, reply.getReplyStep() + 1); // 현재 답글에 대한 답글이므로 reply_step + 1

			pstmt.executeUpdate();

		}catch(SQLException se){
			System.err.println("QnABoardReplyDaoImpl InsertReplyOnReply() Error :" + se.getMessage());
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
	

	/**
	 * 인수로 주어진 Board 객체의 정보로 기존 게시글을 수정한다.
	 * 
	 * @param board 수정할 게시글 정보를 담고 있는 Board 객체
	 */
	@Override
	public void updateReply(QnABoardReply reply) {
		String query="UPDATE QnABoardReply SET replyContent=? WHERE replyNum=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getReplyContent());			
			pstmt.setInt(2, reply.getReplyNum());			

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("QnABoardReplyDaoImpl updateReply() Error :" + se.getMessage());
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

	/**
	 * 인수로 주어진 번호에 해당하는 게시글을 삭제한다.
	 * 
	 * @param num 삭제하려는 게시글의 번호
	 */
	@Override
	public void deleteReply(int replyNum) {
		String query=" DELETE FROM QnABoardReply WHERE replyNum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, replyNum);

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("QnABoardReplyDaoImpl deleteReply() Error :" + se.getMessage());
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
