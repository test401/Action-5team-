package action.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.business.domain.board.AuctionBoard;
import action.business.domain.board.Board;
import action.business.domain.reply.AuctionBoardReply;
import action.business.service.DataNotFoundException;


/**
 * Servlet implementation class ReplyController
 */
public class AuctionReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
	        // action 요청파라미터 값을 확인한다.
	        String action = request.getPathInfo();
	
	        // action 값에 따라 적절한 메소드를 선택하여 호출한다.
	        if (action.equals("/replyForm")) {
	        	replyBoardForm(request, response);
	        } else if (action.equals("/reply")) {
	        	replyBoard(request, response);
	        } else {  	
	        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
        } catch (Exception ex) {
        	throw new ServletException(ex);
        }
    }
	
	
	/* 
     * 답변글 작성을 위해 적절한 내용이 채워진 폼을 응답한다.
     */
	private void replyBoardForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {
		// 요청 파라미터로 부터 글 번호(num)를 구한다.
		String boardNum = request.getParameter("boardNum");
		
		//1.2 pageNumber 요청 파라미터 값을 구한다.
		String pageNumber = request.getParameter("pageNumber");
				
			int currentPageNumber = 1;
			if(pageNumber != null && pageNumber.length() !=0){
				currentPageNumber = Integer.parseInt(pageNumber);
			}
		// BoardService 객체를 통해 해당 번호의 게시글을 검색한다.
		AuctionReplyService service = new AuctionReplyServiceImpl();
        AuctionBoard board = AuctionBoardService.findBoard(Integer.parseInt(boardNum));
        
        // request scope 속성(board)에 검색한 게시글을 저장한다.
        request.setAttribute("board", board);
        request.setAttribute("currentPageNumber", currentPageNumber);
        
        // RequestDispatcher 객체를 통해 뷰 페이지(/WEB-INF/views/board/replyForm.jsp)로 요청을 전달한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/board/replyForm.jsp");
        dispatcher.forward(request, response);
	}
	
	/* 
     * 답변글을 등록하는 요청을 처리한다.
     */
	private void replyBoard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, DataNotFoundException {
		// 1. 요청 파라미터로 부터 글 번호(num), 원본 글 번호(master_num), 작성자(writer), 제목(title), 내용(contents)을 구한다.
		String replyNum = request.getParameter("replyNum");
		String auctionBoardNum = request.getParameter("auctionBoardNum");
		String writer = request.getParameter("writer");
		String replyContent = request.getParameter("replyContent");
		String replyPwd = request.getParameter("replyPwd");
		String masterNum = request.getParameter("masterNum");
		String replyOrder = request.getParameter("replyOrder");
		String replyStep = request.getParameter("replyStep");
		
		// 2. 구해 온 요청 파라미터 값와 ip 값을 지닌 Board 객체를 생성한다.
		AuctionBoardReply reply = new AuctionBoardReply(Integer.parseInt(replyNum) ,writer, replyPwd, 
				replyContent, Integer.parseInt(auctionBoardNum), Integer.parseInt(masterNum), Integer.parseInt(replyOrder), Integer.parseInt(replyStep));
		
		// 3. BoardService 객체를 통해 해당 게시글을 갱신한다.
		AuctionReplyService service = new AuctionReplyServiceImpl();
		service.replyBoard(reply);
   
        // 4. RequestDispatcher 객체를 통해 게시물 목록 보기(list)로 요청을 전달한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("list");
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
