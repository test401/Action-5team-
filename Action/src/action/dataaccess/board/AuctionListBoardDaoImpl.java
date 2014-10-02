package action.dataaccess.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import action.business.domain.board.AuctionListBoard;
import action.business.service.board.AuctionListBoardDao;

public class AuctionListBoardDaoImpl implements AuctionListBoardDao{

	private DataSource dataSource;

	public AuctionListBoardDaoImpl(){
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

	private Connection obtainConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * 조건에 맞는 모든 게시물 목록을 조회한다.
	 * 
	 * @return 검색된 게시물 목록을 담고 있는 List 객체
	 */
	@Override
	public List<AuctionListBoard> selectBoardList(Map<String, Object> searchInfo) {		

		// 1.2. searchInfo Map으로부터 현재 페이지에 보여질 게시글의 행 번호(startRow, endRow) 값을 구한다.
		int startRow = (Integer) searchInfo.get("startRow");
		int endRow = (Integer) searchInfo.get("endRow");
		String memberID = (String) searchInfo.get("memberID");

		AuctionListBoard board = null;

		String query = "SELECT * FROM " 
				+ "(SELECT ROWNUM r, auctionlistboard.listnum, categoryboard.categoryname, auctionboard.title, "
				+ "auctionboard.image, auctionboard.currentprice, auctionboard.endtime "
				+ "FROM (select auctionlistboard.listnum, categoryboard.categoryname, auctionboard.title, "
				+ "auctionboard.image, auctionboard.currentprice, auctionboard.endtime "
				+ "FROM AUCTIONBOARD, CATEGORYBOARD, AUCTIONLISTBOARD "
				+ "WHERE auctionlistboard.memberid = auctionboard.memberid AND auctionboard.memberid = ? "
				+ "AND CATEGORYBOARD.CATEGORYID = AUCTIONBOARD.CATEGORYID ORDER BY listnum DESC) WHERE r BETWEEN ? and ?";

		//String query = "SELECT num, writer, title, read_count, reg_date FROM board " + whereSQL 
		//	+ "ORDER BY num DESC";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AuctionListBoard> boardList = new ArrayList<AuctionListBoard>();

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, memberID);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rs = pstmt.executeQuery();
			String title;

			while(rs.next()){
				title = rs.getString("title");
				if(title.length() > 20){
					title = title.substring(0, 20) + "...";
				}

				board = new AuctionListBoard(
						rs.getInt("listnum"),
						rs.getString("memberID"),
						rs.getInt("boardnum"),
						rs.getInt("price")
						);
				boardList.add(board);
			}

		}catch(SQLException se){
			System.err.println("BoardDAOImpl selectBoardList() Error :" + se.getMessage());
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}

		return boardList;
	}

	/**
	 * 조건에 맞는 모든 게시글 개수를 조회한다.
	 * 
	 * @return 검색된 모든 게시글의 개수
	 */
	@Override
	public int selectBoardCount(Map<String, Object> searchInfo) {
		String memberID = (String) searchInfo.get("memberID");
		
		int boardCount = 0;

		// 4. SELECT 문에 생성된 WHERE 절을 붙인다.
		String query = "SELECT count(listnum) FROM AuctionListBoard where memberID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();

			//boardCount = rs.getInt("count(num)");			

			if(rs.next()){
				boardCount = rs.getInt(1);
			}

		}catch(SQLException se){
			System.err.println("BoardDAOImpl selectBoardCount() Error :" + se.getMessage());
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
	public AuctionListBoard selectBoard(int num) {
		AuctionListBoard board = null;

		String query = "SELECT * FROM AuctionListBoard WHERE listnum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()){
				board = new AuctionListBoard(
						rs.getInt("listnum"),
						rs.getString("memberID"),
						rs.getInt("boardnum"),
						rs.getInt("price"));
			}
		}catch(SQLException se){
			System.err.println("BoardDaoImpl selectBoard() Error :" + se.getMessage());
			se.printStackTrace(System.err);
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}

		return board;
	}

	
	/**
	 * 인수로 주어진 번호에 해당하는 게시글이 있는지 확인한다.
	 * 
	 * @param num 존재여부를 확인하려는 게시글의 번호
	 * @return 해당하는 게시글이 존재하면 true, 존재하지 않으면 false
	 */
	@Override
	public boolean boardNumExists(int num) {
		boolean isExist = false;

		String query="SELECT * FROM AuctionListBoard WHERE listnum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			isExist = rs.next();


		}catch(SQLException se){
			System.err.println("BoardDaoImpl boardNumExists() Error :" + se.getMessage());
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
	public void insertBoard(AuctionListBoard board) {
		String query = "INSERT INTO AuctionListBoard (listnum, boardnum, memberID, price) "
				+ "VALUES (AUCTIONLISTBOARD_LISTNUM_SEQ.NEXTVAL, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, board.getBoardNum());
			pstmt.setString(2, board.getMemberID());
			pstmt.setInt(3, board.getPrice());

			pstmt.executeUpdate();

		}catch(SQLException se){
			System.err.println("BoardDaoImpl insertBoard() Error :" + se.getMessage());
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
	public void deleteBoard(int num) {
		String query=" DELETE FROM AuctionListBoard WHERE boardnum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, num);

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("BoardDaoImpl deleteBoard() Error :" + se.getMessage());
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
