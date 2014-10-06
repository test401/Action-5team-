package action.dataaccess.board;

import java.sql.Connection;
import java.sql.Date;
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

import oracle.sql.BLOB;
import action.business.domain.board.AuctionBoard;
import action.business.service.board.AuctionBoardDao;

public class AuctionBoardDaoImpl implements AuctionBoardDao {

	private DataSource dataSource;

	public AuctionBoardDaoImpl(){
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

	@Override
	public List<AuctionBoard> selectBoardList(Map<String, Object> searchInfo) {
		// 1. searchInfo으로부터 검색 조건을 구한다.
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");
		String categoryType = (String) searchInfo.get("categoryType");

		// 1.2. searchInfo Map으로부터 현재 페이지에 보여질 게시글의 행 번호(startRow, endRow) 값을 구한다.
		int startRow = (Integer) searchInfo.get("startRow");
		int endRow = (Integer) searchInfo.get("endRow");		

		// 2. categoryType과 일치하는 게시물 목록을 출력하기위한 조건절을 생성한다.
		String categorySQL = "";
		String whereSQL = "";

		if((categoryType == null) || (categoryType.length() == 0)){
			categorySQL = "";
		}else{
			categorySQL = "WHERE (" + categoryType.trim() + " LIKE "
					+ "(SELECT CategoryName FROM AuctionBoard, CategoryBoard "
					+ "WHERE AuctionBoard.categoryID = CategoryBoard.categoryID))";
		}

		// 2.1. searchType 값에 따라 사용될 조건절을 생성한다.
		if((searchType == null) || (searchType.length() == 0)){
			whereSQL = "";
		} else if (searchType.equals("all")){
			whereSQL = "where (title LIKE ? OR memberID LIKE ? OR contents LIKE ?)";
		} else if ( (searchType.equals("title")) || (searchType.equals("memberID")) || (searchType.equals("contents")) ){
			whereSQL = "(" + searchType.trim() + " LIKE ?)";
		}

		// 2.2. categorySQL의 유무에 따라 WHERE 혹은 AND절 생성을 결정한다.
		if(categorySQL.length() != 0){
			if(whereSQL.length() == 0){
				whereSQL = "";
			} else {
				whereSQL = "AND " + whereSQL;
			}			
		} else {
			whereSQL = "WHERE " + whereSQL;
		}

		// 3. LIKE 절에 포함될 수 있도록 searchText 값 앞 뒤에 % 기호를 붙인다.
		if(searchText != null){
			searchText = "%" + searchText + "%";
		}

		AuctionBoard board = null;

		String query = "SELECT * FROM " 
				+ "(SELECT ROWNUM r, boardnum, title, memberID, mainImage, categoryID, currentprice, immediatelyprice, endtime FROM "
				+ "(SELECT boardnum, title, memberID, mainImage, categoryID, currentprice, immediatelyprice, endtime FROM AuctionBoard "
				+ " ORDER BY boardnum DESC)) WHERE r BETWEEN ? and ?";		

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AuctionBoard> boardList = new ArrayList<AuctionBoard>();

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);

			// 5. searchType 값에 따라 PreparedStatement의 파라미터 값을 설정한다. (startRow, endRow 값 포함)
			if((searchType == null) || (searchType.length() == 0)){
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);

			} else if (searchType.equals("all")){
				pstmt.setString(1, searchText);
				pstmt.setString(2, searchText);
				pstmt.setString(3, searchText);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);

			} else if ( (searchType.equals("title")) || (searchType.equals("memberID")) || (searchType.equals("contents")) ){
				pstmt.setString(1, searchText);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}

			rs = pstmt.executeQuery();
			String title;

			while(rs.next()){
				title = rs.getString("title");
				if(title.length() > 20){
					title = title.substring(0, 20) + "...";
				}

				board = new AuctionBoard(rs.getInt("boardnum"),
						title,
						rs.getString("memberID"),
						rs.getString("mainImage"),
						rs.getInt("currentPrice"),						
						rs.getInt("categoryID"),
						rs.getInt("immediatelyPrice"),
						rs.getString("endtime"));
				boardList.add(board);
			}

		}catch(SQLException se){
			System.err.println("AuctionBoardDao selectBoardList() Error :" + se.getMessage());
		}finally{
			try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
			try { if (conn != null) conn.close(); } catch(SQLException e) { e.printStackTrace(System.err); }

		}
		System.out.println(boardList);
		return boardList;
	}

	@Override
	public int selectBoardCount(Map<String, Object> searchInfo) {
		// 1. searchInfo으로부터 검색 조건을 구한다.
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");
		String categoryType = (String) searchInfo.get("categoryType");

		// 2. searchType 값에 따라 사용될 조건절을 생성한다.
		String categorySQL = "";
		String whereSQL = "";

		if((categoryType == null) || (categoryType.length() == 0)){
			categorySQL = "";
		}else{
			categorySQL = "WHERE (" + categoryType.trim() + " LIKE "
					+ "(SELECT CategoryName FROM AuctionBoard, CategoryBoard "
					+ "WHERE AuctionBoard.categoryID = CategoryBoard.categoryID))";
		}		

		// 2.1. searchType 값에 따라 사용될 조건절을 생성한다.
		if((searchType == null) || (searchType.length() == 0)){
			whereSQL = "";
		} else if (searchType.equals("all")){
			whereSQL = "(title LIKE ? OR memberID LIKE ? OR contents LIKE ?)";
		} else if ( (searchType.equals("title")) || (searchType.equals("memberID")) || (searchType.equals("contents")) ){
			whereSQL = "(" + searchType.trim() + " LIKE ?)";
		}

		// 2.2. categorySQL의 유무에 따라 WHERE 혹은 AND절 생성을 결정한다.
		if(categorySQL.length() != 0){
			if(whereSQL.length() == 0){
				whereSQL = "";
			} else {
				whereSQL = "AND " + whereSQL;
			}			
		} else {
			whereSQL = "WHERE " + whereSQL;
		}

		// 3. LIKE 절에 포함될 수 있도록 searchText 값 앞 뒤에 % 기호를 붙인다.
		if(searchText != null){
			searchText = "%" + searchText + "%";
		}

		int boardCount = 0;

		// 4. SELECT 문에 생성된 WHERE 절을 붙인다.
		String query = "SELECT count(boardnum) FROM AuctionBoard " + categorySQL + whereSQL;

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

	@Override
	public AuctionBoard selectBoard(int num) {
		AuctionBoard board = null;

		String query = "SELECT * FROM AuctionBoard WHERE boardnum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()){
				board = new AuctionBoard(rs.getInt("boardNum"),
						rs.getString("title"),
						rs.getString("memberID"),
						rs.getString("contents"),
						rs.getString("startTime"),
						rs.getString("endTime"),
						rs.getInt("categoryID"),
						rs.getInt("isImmediately"),
						rs.getInt("startPrice"),
						rs.getInt("immediatelyPrice"),
						rs.getInt("currentPrice"),
						rs.getString("image"),
						rs.getString("mainImage")
						);
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

	@Override
	public boolean boardNumExists(int num) {
		boolean isExist = false;

		String query="SELECT * FROM AuctionBoard WHERE boardnum = ?";

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

	@Override
	public void insertBoard(AuctionBoard board) {
		String query = "INSERT INTO AuctionBoard (boardNum, title, memberID, contents, "
				+ "startTime, endTime, categoryID, isImmediately, startPrice, immediatelyPrice, currentPrice, image, mainImage) "
				+ "VALUES (AUCTIONBOARD_BOARDNUM_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE+"+board.getEndTime()+", ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getMemberID());
			pstmt.setString(3, board.getContents());
//			pstmt.setString(4, board.getEndTime());
			pstmt.setInt(4, board.getCategoryID());
			pstmt.setInt(5, board.getIsImmediately());
			pstmt.setInt(6, board.getStartPrice());
			pstmt.setInt(7, board.getImmediatelyPrice());
			pstmt.setInt(8, board.getCurrentPrice());
			pstmt.setString(9, board.getImage());
			pstmt.setString(10, board.getMainImage());

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

	@Override
	public void updateBoard(AuctionBoard board) {
		String query="UPDATE AuctionBoard SET title = ?, contents = ?, "
				+ "categoryID = ?, isImmediately = ?, immediatelyPrice = ?, "
				+ "image =?, mainImage = ?  WHERE boardnum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContents());
			pstmt.setInt(3, board.getCategoryID());
			pstmt.setInt(4, board.getIsImmediately());
			pstmt.setInt(5, board.getImmediatelyPrice());
			pstmt.setString(6, board.getImage());
			pstmt.setString(7, board.getMainImage());
			pstmt.setInt(8, board.getBoardNum());			

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("BoardDaoImpl updateBoard() Error :" + se.getMessage());
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
	
	@Override
	public void updatePrice(AuctionBoard board) {
		String query="UPDATE AuctionBoard SET currentPrice = ? WHERE boardnum = ?";
	
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = obtainConnection();
			pstmt = conn.prepareStatement(query);			
			pstmt.setInt(1, board.getCurrentPrice());
			pstmt.setInt(2, board.getBoardNum());			

			pstmt.executeUpdate();			

		}catch(SQLException se){
			System.err.println("BoardDaoImpl updatePrice() Error :" + se.getMessage());
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

	@Override
	public void deleteBoard(int num) {
		String query=" DELETE FROM AuctionBoard WHERE boardnum = ?";

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
