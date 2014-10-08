<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="top">
 	<a href="/Action/AuctionBoard?action=main">
		<img id="actionLogo" src="/Action/views/images/actionLogo.gif"
		alt="액션로고 축구공" style="width: 260px; height: 100px">
	</a>
	
	<div class="headermid">
		<div class="buttonbar">
			<form name="searchForm" action="/Action/AuctionBoard?action=list&pageNumber=${currentPageNumber} method = "POST">
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
			<td><label class="category" draggable="true"><a href="/Action/AuctionBoard?action=list">전체 물품 보기</a></label></td>
			<td><label class="category" draggable="true">자유 게시판</label></td>
			<td><label class="category" draggable="true">Q&A</label></td>
			
		</tr>
	</table>
	</div>

	
	<div id ="sidebarMargin">
	<form id="loginck" action="/Action/MemberController?action=login"
		method="POST">
		<c:if test="${empty sessionScope.loginMember}">
			<c:import url="/views/member/login.jsp" />
		</c:if>
		<c:if test="${not empty sessionScope.loginMember}">
			<c:import url="/views/member/logout.jsp" />
		</c:if>
	</form>
	</div>

</header>
