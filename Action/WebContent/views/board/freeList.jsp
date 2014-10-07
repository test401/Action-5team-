<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<link rel="stylesheet" href="/Action/css/board.css">
	<script src="/Action/js/board.js"></script>

	<div class="boardpage">
		<table id="listtable" class="maintable">
	 		<caption>게시글 목록</caption>
			<thead>
				<tr>
					<th class="boardNum"></th>
					<th class="title">제 목</th>
					<th class="memberID">글쓴이</th>

				</tr>
			</thead>
			<tbody>
			<c:if test="${empty freeBoardList}">
				<tr>
					<td colspan="5">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test= "${not empty freeBoardList}">
			<c:forEach var="board" items="${freeBoardList}">
				<tr>
					<td class="boardNum">${freeBoardList.boardNum}</td>
					<td class="title">
				
					<a href="read?pageNumber=${currentPageNumber}&boardNum=${freeBoardList.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}">${freeBoardList.title}</a></td>
					
					<td class="memberID">${freeBoardList.memberID}</td>
				</tr>
			</c:forEach>
			</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td id="pagenavigator" colspan="5">
					<c:if test="${currentPageNumber > 1}">
						<a href="list?pageNumber=${startPageNumber -1}&searchType=${earchType}&searchText=${searchText}">이전</a>
					</c:if>
						
						
						<c:forEach begin="${startPageNumber}" end="${endPageNumber}" var="pageNumber">
							<c:choose>
								<c:when test="${pageNumber eq currentPageNumber}">
									<a class="pagenumber currpage">${pageNumber}</a>
								</c:when>
								<c:otherwise>
									<a class="pagenumber" href="list?pageNumber=${pageNumber}&searchType=${searchType}&searchText=${searchText}">${pageNumber}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test = "${endPageNumber < totalPageCount}">
							<a href="list?pageNumber=${endPageNumber +1}&searchType=${earchType}&searchText=${searchText}">다음</a>
						</c:if>
					</td>
				</tr>
			</tfoot>
		</table>
		<div class="buttonbar">
			<form name="searchForm" action="/Action/FreeBoard?action=list&searchType=${param.searchType}&searchText=${param.searchText}" method="GET" onsubmit="return searchCheck();">
				<select name="searchType">
					<option value="all" <c:if test = "${empty param.searchType}">selected="selected"</c:if>>전체검색</option>
					<option value="title" <c:if test = "${param.searchType=='title'}">selected="selected"</c:if>>제목</option>
					<option value="writer" <c:if test = "${param.searchType=='memberID'}">selected="selected"</c:if>>글쓴이</option>
					<option value="contents" <c:if test = "${param.searchType=='contents'}">selected="selected"</c:if>>내용</option>
				</select> 
				<input id="searchinput" type="text" name="searchText" value="${param.searchText}">
				<input type="submit" value="검색"> 
				<input type="button" value="목록" onclick="goUrl('/Action/FreeBoard?action=list');">
				<input type="button" value="글쓰기" onclick="goUrl('/Action/FreeBoard?action=writeForm');">
			</form>
		</div>
	</div>
