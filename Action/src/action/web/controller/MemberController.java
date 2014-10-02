package action.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.business.*;
import action.business.domain.member.Member;
import action.business.service.DataDuplicatedException;
import action.business.service.DataNotFoundException;
import action.business.service.member.MemberService;
import action.business.service.member.MemberServiceImpl;



/**
 * Servlet implementation class MemberController
 */
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

//		request.setCharacterEncoding("UTF-8");
		//1. action 요청파라미터 값을 확인한다.
		String action = request.getParameter("action");
		/*
		 * 2. action 값(select/register)에따라 적절한 메소드를 선택하여 호출한다.(select 이면
		 * selectMember(), register 이면 registerMember() , login 이면
		 * loginMember(), logout 이면 logoutMember(), update 이면 updateMember()메소드
		 * , remove 이면 removeMember()메소드 호출)
		 */
		try{
			if (action.equals("select")) {
				selectMember(request, response);
			} else if (action.equals("register")) {
				registerMember(request, response);
			} else if (action.equals("login")) {
				login(request, response);
			} else if (action.equals("logout")) {
				logout(request, response);
			} else if (action.equals("update")) {
				updateMember(request, response);
			} else if (action.equals("remove")) {
				removeMember(request, response);
			}

		}catch(DataNotFoundException dne){
			throw new ServletException(dne);
		}catch(DataDuplicatedException dde){
			throw new ServletException(dde);
		}

	}


	/**
	 * 회원의 로그인 요청을 처리한다.
	 */
	private void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 요청 파라미터를 통해 HTML 폼 데이터를 얻어낸다.
		String memberID = request.getParameter("memberID");
		String password = request.getParameter("password");

		// MemberService 객체에 위임하여 유효한 회원인지 확인한다.
		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.loginCheck(memberID, password);
		// 결과를 받은 member 객체 체크를 하여 유효한 아이디인지 아이디가 없는지 패스워드가 틀린 것인지 확인하는 것이다.
		
		// check 값을 확인하여
		int check=member.getLoginCheck();
		//유효한 아이디인 경우 
		if (check == Member.VALID_MEMBER) {
//			//request scope 속성에 유효한 회원을 저장한다.
//			request.setAttribute("loginMember", member);
			//session객체를 생성 후 session scope 속성에 로그인된 회원 객체를 저장하고 
			HttpSession session = request.getSession(true);
			session.setAttribute("loginMember", member);
			// RequestDispatcher 객체를 통해 뷰 서블릿(logout.jsp)으로 요청을 전달한다.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/main.jsp");
			dispatcher.forward(request, response);
		//유효하지 않는 아이디인 경우
		} else {
			//에러메시지 변수
			String loginErrorMsg=null;
			//패스워드가 틀린 경우
			if (check == Member.INVALID_PASSWORD) {
				loginErrorMsg="패스워드를 확인해주세요.";
			}
			//아이디가 없는 경우
			if (check == Member.INVALID_ID) {
				loginErrorMsg="회원아이디를 확인해주세요.";
			}
			// 에러 내용을 request scope 속성에 저장하고
			request.setAttribute("loginErrorMsg", loginErrorMsg);
			// 에러 페이지 뷰 서블릿(userError.jsp)으로 요청을 전달한다.
			RequestDispatcher dispathcer = request.getRequestDispatcher("/views/main.jsp");
			dispathcer.forward(request, response);
		}
	}
	
	
	/**
	 * 회원의 로그아웃 요청을 처리한다.
	 */
	private void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//session scope에 저장된 속성들(회원정보들)을 제거하고 세션을 무효화한다.
		HttpSession session = request.getSession(false);
		if(session!=null){
			//session.invalidate(); 비추
			session.removeAttribute("loginMember");
		}
		// index.jsp페이지로 전달한다.(맨처음 페이지 표시하기 위함)
		RequestDispatcher dispathcer = request.getRequestDispatcher("/views/main.jsp");
		dispathcer.forward(request, response);
	}

	
	/**
	 * 새로운 회원을 등록하는 요청을 처리한다.
	 * @throws DataDuplicatedException 
	 */
	private void registerMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, DataDuplicatedException {

		// 3.1. 요청 파라미터를 통해 HTML 폼 데이터를 얻어낸다.
		String memberID = request.getParameter("memberID");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		int warnCount = 0;
		int isAdmin = 0;

		// 3.2. 폼 데이터의 유효성을 검증하는 처리를 한다.
		// 3.2.1. 에러 메시지들을 저장할 리스트
		List<String> errorMsgs = new ArrayList<String>();

		// 3.2.2. 폼 데이터가 유효한 지 검증
		if ((memberID == null) || (memberID.length() == 0)) {
			errorMsgs.add("회원아이디를 입력해주세요.");
		}
		if ((password == null) || (password.length() == 0)) {
			errorMsgs.add("패스워드를 입력해주세요.");
		}
		if ((name == null) || (name.length() == 0)) {
			errorMsgs.add("이름을 입력해주세요.");
		}
		if ((tel == null) || (tel.length() == 0)) {
			errorMsgs.add("연락처를 입력해주세요.");
		}

		// 3.2.3. 유효하지 않은 데이터가 있으면
		if (!errorMsgs.isEmpty()) {
			// 3.2.4. 에러 내용을 request scope 속성에 저장하고
			request.setAttribute("errorMsgs", errorMsgs);
			// 3.2.5. 에러 페이지 뷰 서블릿(userError.jsp)으로 요청을 전달한다.
			RequestDispatcher dispathcer = request.getRequestDispatcher("userError.jsp");
			dispathcer.forward(request, response);
			return;
		}

		// 3.3. 새로운 회원을 등록하는 처리를 한다.
		// 3.3.1. 적절한 데이터를 가진 Member 객체를 생성하여
		Member member = new Member(memberID, password, name, address,
				tel, warnCount, isAdmin);

		// 3.3.2. MemberService 객체에 위임하여 회원을 등록한다.
		MemberService memberService = new MemberServiceImpl();
		memberService.registerMember(member);

		// 3.3.3. request scope 속성에 등록된 member를 저장하고
		request.setAttribute("member", member);
		// 3.3.4. RequestDispatcher 객체를 통해 뷰 서블릿(thankYou.view)으로 요청을 전달한다.
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("thankYou.view");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/member/registerSuccess.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * 기존 회원의 정보수정하는 요청을 처리한다.
	 * @throws DataNotFoundException 
	 */
	private void updateMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
//		// 요청 파라미터를 통해 HTML 폼 데이터를 얻어낸다.
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		
		// 폼 데이터의 유효성을 검증하는 처리를 한다.
		// 에러 메시지들을 저장할 리스트
		List<String> errorMsgs = new ArrayList<String>();
		// 폼 데이터가 유효한 지 검증
		if ((password == null) || (password.length() == 0)) {
			errorMsgs.add("패스워드를 입력해주세요.");
		}
		if ((name == null) || (name.length() == 0)) {
			errorMsgs.add("이름을 입력해주세요.");
		}
		if ((tel == null) || (tel.length() == 0)) {
			errorMsgs.add("전화를 입력해주세요.");
		}
		if ((address == null) || (address.length() == 0)) {
			errorMsgs.add("주소를 입력해주세요.");
		}
		// 유효하지 않은 데이터가 있으면
		if (!errorMsgs.isEmpty()) {
			// 에러 내용을 request scope 속성에 저장하고
			request.setAttribute("errorMsgs", errorMsgs);
			// 에러 페이지 뷰 서블릿(userError.jsp)으로 요청을 전달한다.
			RequestDispatcher dispathcer = request.getRequestDispatcher("userError.jsp");
			dispathcer.forward(request, response);
			return;
		}
//		// 정보수정 할 회원의 정보수정하는 처리를 한다.
//		// 적절한 데이터를 가진 Member 객체를 생성하여
//		Member member = new Member(memberID, password, name, email, tel,
//				zipcode, address);
		
		//session을 통해서 데이터 가져오기
		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"로그인이 필요합니다.");
			return;
		}
		Member member = (Member)session.getAttribute("loginMember");
		
		if(member==null){
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"로그인이 필요합니다.");
			return;
		}
		member.setPassword(password);
		member.setName(name);
		member.setTel(tel);
		member.setAddress(address);
		// MemberService 객체에 위임하여 회원 정보를 수정한다.
		MemberService memberService = new MemberServiceImpl();
		memberService.updateMember(member);

		// request scope 속성에 정보수정된 member를 저장하고
		request.setAttribute("member", member);
		// RequestDispatcher 객체를 통해 뷰 서블릿(thankYou.jsp)으로 요청을 전달한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/member/infomationMember.jsp");
		dispatcher.forward(request, response);

	}
	
	/**
	 * 회원을 검색하는 요청을 처리한다.
	 * @throws DataNotFoundException 
	 */
	private void selectMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {

//		// 1.1. memberID 요청파라미터 값을 확인한다.
//		String memberID = request.getParameter("memberID");
//		if ((memberID == null) || (memberID.length() == 0)) {
//			memberID = "duke";
//		}
		// 1.1. session scope 속성에서 회원정보를 찾아 memberID 값을 확인한다.
		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"로그인이 필요합니다.");
			return;
		}
		Member member = (Member)session.getAttribute("loginMember");
		
		if(member==null){
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"로그인이 필요합니다.");
			return;
		}
		// 1.2. 비즈니스 로직을 수행할 MemberService 객체를 생성하여
		MemberService memberService = new MemberServiceImpl();

		// 1.3. memberID로 회원를 검색한다.(MemberService의 findMember() 사용)
		Member selectedMember = memberService.findMember(member.getMemberID());

		// request scope 속성에 검색된 회원 객체를 저장한다.
		request.setAttribute("selectedMember", selectedMember);
		
		// RequestDispatcher를 통해 view 서블릿으로 요청 전달한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/member/infomationMember.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * 기존 회원의 회원탈퇴 요청을 처리한다.
	 * @throws DataNotFoundException 
	 */
	private void removeMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {

		//session을 통해서 데이터 가져오기
		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"로그인이 필요합니다.");
			return;
		}
		Member member = (Member)session.getAttribute("loginMember");
		
		if(member==null){
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"로그인이 필요합니다.");
			return;
		}

		// MemberService 객체에 위임하여 회원을 탈퇴시킨다.
		MemberService memberService = new MemberServiceImpl();
		memberService.removeMember(member);

		// request scope 속성에 정보수정된 member를 저장하고
		request.setAttribute("member", member);
		//session scope 속성에 있는 loginMember 제거 후
		session.removeAttribute("loginMember");
		
		// RequestDispatcher 객체를 통해 뷰 서블릿(goodByeYou.jsp)으로 요청을 전달한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/member/leaveSuccess.jsp");
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
