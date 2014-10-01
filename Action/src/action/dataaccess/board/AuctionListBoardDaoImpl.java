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
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/dukeshopDB");
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
				+ "from AUCTIONBOARD, CATEGORYBOARD, AUCTIONLISTBOARD "
				+ "where auctionlistboard.memberid = auctionboard.memberid AND auctionboard.memberid = ? "
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

				board = new AuctionListBoard(rs.getInt("boardnum"),
						rs.getString("memberID"),
						rs.getInt("listnum"));
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
		// 1. searchInfo으로부터 검색 조건을 구한다.
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");

		// 2. searchType 값에 따라 사용될 조건절을 생성한다.
		String whereSQL = "";

		if((searchType == null) || (searchType.length() == 0)){
			whereSQL = "";
		} else if (searchType.equals("all")){
			whereSQL = "WHERE title LIKE ? OR writer LIKE ? OR contents LIKE ?";
		} else if ( (searchType.equals("title")) || (searchType.equals("writer")) || (searchType.equals("contents")) ){
			whereSQL = "WHERE " + searchType.trim() + " LIKE ?";
		}

		// 3. LIKE 절에 포함될 수 있도록 searchText 값 앞 뒤에 % 기호를 붙인다.
		if(searchText != null){
			searchText = "%" + searchText + "%";
		}

		int boardCount = 0;

		// 4. SELECT 문에 생성된 WHERE 절을 붙인다.
		String query = "SELECT count(num) FROM AuctionListBoard " + whereSQL;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			// 5. searchType 값에 따라 PreparedStatement의 파라미터 값을 설정한다.
			if((searchType == null) || (searchType.length() == 0)){

			} else if (searchType.equals("all")){
				pstmt.setString(1, searchText);
				pstmt.setString(2, searchText);
				pstmt.setString(3, searchText);

			} else if ( (searchType.equals("title")) || (searchType.equals("writer")) || (searchType.equals("contents")) ){
				pstmt.setString(1, searchText);
			}

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

		String query = "SELECT * FROM AuctionListBoard WHERE num = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()){
				board = new AuctionListBoard(rs.getInt("num"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("contents"),
						rs.getString("ip"),
						rs.getInt("read_count"),
						rs.getDate("reg_date").toString(),
						rs.getDate("mod_date").toString(),
						rs.getInt("master_num"),
						rs.getInt("reply_order"),
						rs.getInt("reply_step"));
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

		String query="SELECT * FROM AuctionListBoard WHERE num = ?";

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
		String query = "INSERT INTO AuctionListBoard (num, writer, title, contents, ip, read_count, reg_date, "
				+ "mod_date, master_num) VALUES (board_num_seq.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, SYSDATE, board_num_seq.CURRVAL)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContents());
			pstmt.setString(4, board.getIp());

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
	 * 인수로 주어진 Board 객체의 정보로 기존 게시글을 수정한다.
	 * 
	 * @param board 수정할 게시글 정보를 담고 있는 Board 객체
	 */
	@Override
	public void updateBoard(AuctionListBoard board) {
		String query="UPDATE AuctionListBoard SET writer=?, title=?, contents=?, "
				+ "ip=?, mod_date=SYSDATE WHERE num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContents());
			pstmt.setString(4, board.getIp());
			pstmt.setInt(5, board.getNum());

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("BoardDaoImpl addReadCount() Error :" + se.getMessage());
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
		String query=" DELETE FROM AuctionListBoard WHERE num = ?";

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
