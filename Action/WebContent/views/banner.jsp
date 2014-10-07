<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="top">
	<img id="actionLogo" src="/Action/views/images/actionLogo.gif"
		alt="액션로고 축구공" style="width: 260px; height: 100px">
	<embed id="actionLogo" autoplay="true" autostart="true">
	
	
	<div class="headermid">
		<div class="buttonbar">
			<form name="searchForm" action="list?pageNumber=${currentPageNumber}">
				<select name="searchType">
					<option value="all"
						<c:if test="${empty param.searchType || param.searchType=='all'}">
			selected="selected"
			</c:if>>전체검색</option>
					<option value="title"
						<c:if test="${param.searchType=='title'}">
			selected="selected"
			</c:if>>제목</option>
					<option value="writer"
						<c:if test="${param.searchType=='writer'}">
			selected="selected"
			</c:if>>글쓴이</option>
					<option value="contents"
						<c:if test="${param.searchType=='contents'}">
			selected="selected"
			</c:if>>내용</option>
				</select> <input id="searchinput" type="text" name="searchText" size="20"
					value="${param.searchText}" onfocus="this.select();"> <input
					type="button" value="검색" onclick="searchCheck(this.form);">
			</form>
		</div>


		<table id="categorytable">
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr>
			<td><label class="category" draggable="true">회사소개</label></td>
			<td><label class="category" draggable="true">전체 물품 보기</label></td>
			<td><label class="category" draggable="true">자유 게시판</label></td>
			<td><label class="category" draggable="true">Q&A</label></td>
			
		</tr>
	</table>
	</div>



		<form id="loginck" action="/Action/MemberController?action=login"
			method="POST">
			<tr>
				<td class="loginlabel">아이디</td>
				<td><input type="text" name="loginmemberID" size="10"></td>
				
				<td class="loginlabel">패스워드</td>
				<td><input type="password" name="loginpassword" size="10"></td>
				<td colspan="1"><input type="button" id="loginButton"
					name="login" value="로그인"></td>
					</tr>

		<div id="findid">
			<td colspan="1"><label class="memberFont"><a
					href="<c:url value='/views/member/registerMember.jsp' />">회원가입</a>
			</label></td>
			<td></td>
			<td colspan="1"><label class="memberFontID"><a href="">아이디/비밀번호찾기</a></label></td>
			</tr>
		</div>
	</form>
       
        
</header>
