package action.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import action.business.domain.board.*;
import action.business.domain.member.Member;
import action.business.service.DataNotFoundException;
import action.business.service.board.*;
import action.util.PageHandler;

/**
 * Servlet implementation class AuctionBoardController
 */
public class AuctionBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private File uploadDir;
	
	@Override
	public void init() throws ServletException {
		uploadDir = new File(getInitParameter("uploadDir"));
		if (!uploadDir.exists()) { uploadDir.mkdir(); }
	}
	

	 /* 
     * HTTP GET과 POST 방식의 요청을 모두 처리한다.
     * 요청파라미터 값을 확인하여 적절한 사용자의 요청을 처리한다.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
	        // action 요청파라미터 값을 확인한다.
	        String action = request.getParameter("action");
	
	        // action 값에 따라 적절한 메소드를 선택하여 호출한다.
	        if (action.equals("list")) {
	        	selectBoardList(request, response);
	        } else if (action.equals("read")) {
	        	readBoard(request, response);
	        } else if (action.equals("writeForm")) {
	        	writeBoardForm(request, response);
	        } else if (action.equals("write")) {
	        	writeBoard(request, response);
	        } else if (action.equals("updateForm")) {
	            updateBoardForm(request, response);
	        } else if (action.equals("update")) {
	            updateBoard(request, response);
	        } else if (action.equals("remove")) {
	            removeBoard(request, response);
	        } else {  	
	        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
        } catch (Exception ex) {
        	throw new ServletException(ex);
        }
    }
    
	/* 
     * 조건에 맞는 모든 경매 게시물 목록을 보여주는 요청을 처리한다.
     */
	private void selectBoardList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		//1. searchType, searchText, searchCategory 요청 파라미터 값을 구한다.
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		String categoryType = request.getParameter("categoryType");
				
		//1.2 pageNumber 요청 파라미터 값을 구한다.
		String pageNumber = request.getParameter("pageNumber");
		
		//(1) 현재 페이지 번호
		int currentPageNumber = 1;
			if(pageNumber != null && pageNumber.length() !=0){
				currentPageNumber = Integer.parseInt(pageNumber);
			}
	
		//2. 검색 옵션을 담고 있는 MAP 객체를 생성하여 searchType, searchText, searchCategory 값을 저장한다.
				Map<String, Object> searchInfo = new HashMap<String, Object>();
				searchInfo.put("searchType", searchType);
				searchInfo.put("searchText", searchText); 
				searchInfo.put("categoryType", categoryType);
				
		//  BoardService 객체로부터 모든 게시글 리스트를 구해온다.
		AuctionBoardService service = new AuctionBoardServiceImpl();
		
		// 전체 게시글 개수
		int totalBoardCount = service.getBoardCount(searchInfo);
		// 전체 페이지 개수
		int totalPageCount = PageHandler.getTotalPageCount(totalBoardCount);

		// 페이지 선택 바에 표시될 시작 페이지 번호
		int startPageNumber = PageHandler.getStartPageNumber(currentPageNumber);
		// 페이지 선택 바에 표시될 끝 페이지 번호
		int endPageNumber = PageHandler.getEndPageNumber(currentPageNumber, totalBoardCount);
		
		// 현재 페이지의 게시글 목록에서 처음 보여질 게시글의 행 번호
		int startRow = PageHandler.getStartRow(currentPageNumber);
		// 현재 페이지의 게시글 목록에서 마지막에 보여질 게시글의 행 번호
		int endRow = PageHandler.getEndRow(currentPageNumber);
				
		// 검색 옵션  Map 객체(searchInfo)에 startRow와 endRow 값을 저장한다.
		searchInfo.put("startRow", startRow);
		searchInfo.put("endRow", endRow);
		
		//3. BoardService 객체로부터 모든 게시글 리스트를 구해온다.
		Board[] auctionList = service.getBoardList(searchInfo);
			
        //4.1  request scope 속성(auctionList)에 게시글 리스트를 저장한다.
		request.setAttribute("auctionList", auctionList);
		
		//4.2 request scope 속성으로 
		request.setAttribute("currentPageNumber", currentPageNumber);
		request.setAttribute("startPageNumber", startPageNumber);
		request.setAttribute("endPageNumber", endPageNumber);
		request.setAttribute("totalPageCount", totalPageCount);
        
        //  RequestDispatcher 객체를 통해 뷰 페이지(/views/board/auctionList.jsp)로 요청을 전달한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/board/auctionList.jsp");
        dispatcher.forward(request, response);
		
        
	}
    
    
    
    /* 
     * 선택된 경매글을 읽어와서 보여주는 요청을 처리한다.
     */
	private void readBoard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {
		// 1. 요청 파라미터(num)로 부터 글 번호를 구한다.
		String boardNum = request.getParameter("boardNum");
		//1.2 pageNumber 요청 파라미터 값을 구한다.
		String pageNumber = request.getParameter("pageNumber");
		
		int currentPageNumber = 1;
			if(pageNumber != null && pageNumber.length() !=0){
				currentPageNumber = Integer.parseInt(pageNumber);
			}
		// 2. BoardService 객체로부터 해당 글 번호의 게시글을 구해온다.
		AuctionBoardService service = new AuctionBoardServiceImpl();

		AuctionBoard auctionBoard = service.findBoard(Integer.parseInt(boardNum));

		
		// 3. request scope 속성(board)에 게시글을 저장한다.
        request.setAttribute("auctionBoard", auctionBoard);

        // 3.2 request scope 속성으로 currentPageNumber를 저장한다.
		request.setAttribute("currentPageNumber", currentPageNumber);
		
        // 4. RequestDispatcher 객체를 통해 뷰 페이지(/views/board/auctionRead.jsp)로 요청을 전달한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/board/auctionRead.jsp");
        dispatcher.forward(request, response);
        
	}
	
	/* 
     * 경매 입찰을 위한 요청을 처리한다.
     */
	public void bidAuction(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {
		
		String currentPrice = request.getParameter("currentPrice");
		String memberID = request.getParameter("memberID");
		String boardNum = request.getParameter("boardNum");
		
		// 구해 온 요청 파라미터 값을 지닌 AuctionBoard 객체를 생성한다.
		AuctionBoard auctionBoard = new AuctionBoard(Integer.parseInt("boardNum") ,Integer.parseInt("currentPrice"));
		// AuctionBoardService 객체를 통해 현재입찰금을 갱신한다.
		AuctionBoardService auctionService = new AuctionBoardServiceImpl();
		auctionService.updatePrice(auctionBoard);
		
		// 구해 온 요청 파라미터 값을 지닌 BidBoard 객체를 생성한다.
		BidListBoard bidBoard = new BidListBoard(Integer.parseInt("boardNum"), memberID, Integer.parseInt("currentPrice"));
		// BidListBoardService 객체를 통해 입찰목록을 등록한다.
		BidListBoardService bidService = new BidListBoardServiceImpl();
		bidService.writeBoard(bidBoard);
		
        // RequestDispatcher 객체를 통해 목록 보기(read)로 요청을 전달한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("read");
        dispatcher.forward(request, response);
	}
	
	/* 
     * 입찰이 완료됬을 때를 위한 요청을 처리한다.
     */
	public void AuctionList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {
		
		String memberID = request.getParameter("memberID");
		String boardNum = request.getParameter("boardNum");
		String price = request.getParameter("price");
		
		// 2. 구해 온 요청 파라미터 값을 지닌 BidBoard 객체를 생성한다.
		AuctionListBoard board = new AuctionListBoard(memberID, Integer.parseInt("boardNum"), 
				 Integer.parseInt("price"));
				
		// 3. BoardService 객체를 통해 해당 게시글을 등록한다.
		AuctionListBoardService service = new AuctionListBoardServiceImpl();
		service.writeBoard(board);
		
		

        // RequestDispatcher 객체를 통해 목록 보기(list)로 요청을 전달한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("list");
        dispatcher.forward(request, response);
	}
	
	
	/* 
     * 경매글 등록을 위한 폼을 응답한다.
     */
	private void writeBoardForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {
		
        // RequestDispatcher 객체를 통해 뷰 페이지(/views/board/auctionWriteForm.jsp)로 요청을 전달한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/board/auctionWriteForm.jsp");
        dispatcher.forward(request, response);
	}
	
	/* 
     * 경매글을 등록하는 요청을 처리한다.
     */
	private void writeBoard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
			
		
		// multipart 컨텐트 여부 확인
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new IOException("요청 내용이 multipart/form-data가 아닙니다.");
			
			//return;
		}
		HttpSession session = request.getSession(true);
		Member member = (Member) session.getAttribute("loginMember");
		// 요청 파라미터
		String title = "";
		String memberID = member.getMemberID();
		String contents = null;
		String endTime = null;
		String categoryID = null;
		String isImm = null;
		String startPrice = null;
		String immPrice = null;
		String image = null;
		String mainImage = null;
		
		
		String[] names = {categoryID, title, startPrice, immPrice, isImm , endTime,  contents};
		String[] images = {mainImage, image};
		
		// 디스크 기반의 FileItem factory 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// repository 생성 (a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		// File repository = new File(uploadDir, "temp");  
		// if (!repository.exists()) { repository.mkdir(); }
		
		// factory 제약 설정
		factory.setSizeThreshold(1024 * 100); // 메모리에 저장할 최대 size (100K까지는 메모리에 저장)
		factory.setRepository(repository);	// 파일 임시 저장소 (100K 이상이면 repository에 저장)	
		
		// 파일 업로드 핸들러 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 총 request size 제약 설정
		upload.setSizeMax(1024 * 1024 * 20); // 최대 size (20M까지 가능)
		
		// 요청 파싱
		try {
			List<FileItem> items = upload.parseRequest(request);
			int count = 0;
			int count2 = 0;
			// 업로드된 items 처리
			Iterator<FileItem> iter = items.iterator();
			
			while (iter.hasNext()) {    
				FileItem item = iter.next();
				// 일반 폼 필드 처리 (<input type="file">이 아닌 경우)
				if (item.isFormField()) {
						
					names[count] = item.getString("UTF-8");

					System.out.println(count + " : " + names[count]);
					
					count++;
					
				 // 파일 업로드 처리 (<input type="file">인 경우)
				} else {
					
					
					images[count2] = item.getName();// 경로가 포함된 파일명
					//if(images[count] != null && images[count])
					int index = images[count2].lastIndexOf("\\"); // 디렉터리 구분자 위치를 통해
					if (index == -1) {
						index = images[count2].lastIndexOf("/");
					}
					images[count2] = images[count2].substring(index + 1); // 파일명만 추출
					
					// 파일 업로드 처리
//					File uploadedFile = new File(uploadDir, images[count2]);
//					item.write(uploadedFile); // 실질적인 저장
					System.out.println(count2 + " : " + images[count2]);
		
					count2++;
		
					//names[0] = categoryID
					//names[1] = title,     names[2] = startPrice
					//names[3] = immPrice,  names[4] = isImm
					//names[5] = endTime,   names[6] = contents

				}
			}
			/*out.println(names[0] + " " + names[1]+ " " + names[2]+ " " + names[3]+ " " + " " + images[0] + " "+ images[1] + " "+ startPrice);*/
			//out.close();
			AuctionBoard board;
			
			//즉시 구매 여부에 따라 생성자를 구별
			if(names[6] != null){		
				//즉시 구매가 가능 할 때 : 즉시 구매 여부 값 1, 즉시 구매 가격 입력 활성화
				board = new AuctionBoard(memberID, Integer.parseInt(names[0]),
						names[1], Integer.parseInt(names[2]), Integer.parseInt(names[3]),
						Integer.parseInt(names[4]), names[5], names[6], 0, images[0], images[1]);
			}else{
				//즉시 구매가 불가능 할 때 : 즉시 구매 여부 값 0, 즉수 구매 가격 입력 비활성화
				board = new AuctionBoard(memberID, Integer.parseInt(names[0]),
						names[1], Integer.parseInt(names[2]), 0,
						0, names[3], names[4], 0, images[0], images[1]);
			}
			// 구해 온 요청 파라미터 값을 지닌 AuctionBoard 객체를 생성한다.
			
			System.out.println(board);
			// 3. AuctionBoardService 객체를 통해 해당 게시글을 등록한다.
			AuctionBoardService service = new AuctionBoardServiceImpl();
			service.writeBoard(board);
			
	        //  RequestDispatcher 객체를 통해 뷰 페이지(/views/board/auctionList.jsp)로 요청을 전달한다.
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/AuctionBoard?action=list");
	        dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		
	}

	/* 
     * 경매글 수정을 위해 적절한 내용이 채워진 폼을 응답한다.
     */
	private void updateBoardForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {
		// 요청 파라미터로 부터 경매글 번호(boardNum)를 구한다.
		String boardNum = request.getParameter("boardNum");
		
		//1.2 pageNumber 요청 파라미터 값을 구한다.
		String pageNumber = request.getParameter("pageNumber");
				
			int currentPageNumber = 1;
				if(pageNumber != null && pageNumber.length() !=0){
					currentPageNumber = Integer.parseInt(pageNumber);
				}
		// AuctionBoardService 객체를 통해 해당 번호의 게시글을 검색한다.
        AuctionBoardService boardService = new AuctionBoardServiceImpl();
        Board auctionBoard = boardService.findBoard(Integer.parseInt(boardNum));
        
        // request scope 속성(board)에 검색한 게시글을 저장한다.
        request.setAttribute("auctionBoard", auctionBoard);
        request.setAttribute("currentPageNumber", currentPageNumber);

		//request.setAttribute("searchType", searchType);
		//request.setAttribute("searchText", searchText);
        
        // RequestDispatcher 객체를 통해 뷰 페이지(/views/board/updateForm.jsp)로 요청을 전달한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/board/auctionUpdateForm.jsp");
        dispatcher.forward(request, response);
	}
	
	/* 
     * 경매글을 수정하는 요청을 처리한다.
     */
	private void updateBoard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();		
		
		// multipart 컨텐트 여부 확인
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			out.println("요청 내용이 multipart/form-data가 아닙니다.");
			out.close();
			return;
		}

		HttpSession session = request.getSession(true);
		Member member = (Member) session.getAttribute("loginMember");
		// 요청 파라미터
		String memberID = member.getMemberID();
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String title = "";
		String contents = null;
		String categoryID = null;
		String isImm = null;
		String startPrice = null;
		String immPrice = null;
		String image = null;
		String mainImage = null;
		String endTime = null;
		

		String[] names = {categoryID, title, startPrice, immPrice, isImm , endTime,  contents, "button"};
		String[] images = {mainImage, image};
		
		// 디스크 기반의 FileItem factory 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// repository 생성 (a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		// File repository = new File(uploadDir, "temp");  
		// if (!repository.exists()) { repository.mkdir(); }
		
		// factory 제약 설정
		factory.setSizeThreshold(1024 * 100); // 메모리에 저장할 최대 size (100K까지는 메모리에 저장)
		factory.setRepository(repository);	// 파일 임시 저장소 (100K 이상이면 repository에 저장)	
		
		// 파일 업로드 핸들러 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 총 request size 제약 설정
		upload.setSizeMax(1024 * 1024 * 20); // 최대 size (20M까지 가능)
		
		// 요청 파싱
		try {
			List<FileItem> items = upload.parseRequest(request);
			int count = 0;
			int count2 = 0;
			// 업로드된 items 처리
			Iterator<FileItem> iter = items.iterator();
			
			while (iter.hasNext()) {    
				FileItem item = iter.next();
				// 일반 폼 필드 처리 (<input type="file">이 아닌 경우)
				if (item.isFormField()) {
						
					names[count] = item.getString("UTF-8");

					System.out.println(count + " : " + names[count]);
//					out.println(count+"1:"+names[count]);
					count++;
					
				 // 파일 업로드 처리 (<input type="file">인 경우)
				} else {
		
					images[count2] = item.getName();// 경로가 포함된 파일명
					//if(images[count] != null && images[count])
					int index = images[count2].lastIndexOf("\\"); // 디렉터리 구분자 위치를 통해
					if (index == -1) {
						index = images[count2].lastIndexOf("/");
					}
					images[count2] = images[count2].substring(index + 1); // 파일명만 추출
					
					System.out.println(count2 + "2: " + images[count2]);
//					out.println(count2+":"+images[count2]);
					count2++;
					
					//names[0] = categoryID
					//names[1] = title,     names[2] = startPrice
					//names[3] = immPrice,  names[4] = isImm
					//names[5] = endTime,   names[6] = contents
			
				}
			}
			/*out.println(names[0] + " " + names[1]+ " " + names[2]+ " " + names[3]+ " " + " " + images[0] + " "+ images[1] + " "+ startPrice);*/
			//out.close();
			AuctionBoard board;
			
			//즉시 구매 여부에 따라 생성자를 구별
			if(names[4] != null){		
				//즉시 구매가 가능 할 때 : 즉시 구매 여부 값 1, 즉시 구매 가격 입력 활성화
				board = new AuctionBoard(boardNum, memberID, Integer.parseInt(names[0]),
						names[1], Integer.parseInt(names[2]), Integer.parseInt(names[3]),
						Integer.parseInt(names[4]), names[5], names[6], images[0], images[1]);
			}else{
				//즉시 구매가 불가능 할 때 : 즉시 구매 여부 값 0, 즉수 구매 가격 입력 비활성화
				board = new AuctionBoard(boardNum, memberID, Integer.parseInt(names[0]),
						names[1], Integer.parseInt(names[2]), 0,
						0, names[3], names[4],  images[0], images[1]);
			}
			// 구해 온 요청 파라미터 값을 지닌 AuctionBoard 객체를 생성한다.
			
			// 3. BoardService 객체를 통해 해당 게시글을 등록한다.
			AuctionBoardService service = new AuctionBoardServiceImpl();
			service.updateBoard(board);
			
			
			request.setAttribute("auctionBoard", board);
			
	        //  RequestDispatcher 객체를 통해 뷰 페이지(/views/board/auctionList.jsp)로 요청을 전달한다.
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/AuctionBoard?action=read");
	        dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
		} 	        
	}

	/* 
     * 경매글을 삭제하는 요청을 처리한다.
     */
	private void removeBoard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {
		// 1. 요청 파라미터로 부터 경매글 번호(boardNum)를 구한다.
		String boardNum = request.getParameter("boardNum");
	
		// 2. BoardService 객체를 통해 해당 번호의 게시글을 삭제한다.
		AuctionBoardService service = new AuctionBoardServiceImpl();
		service.removeBoard(Integer.parseInt(boardNum));

        // 3. RequestDispatcher 객체를 통해 목록 보기(list)로 요청을 전달한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AuctionBoard?action=list");
	    dispatcher.forward(request, response);
		
	}
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
