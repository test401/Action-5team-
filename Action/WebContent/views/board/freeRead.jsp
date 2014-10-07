<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<title>게시글 보기</title>
	<link rel="stylesheet" href="Action/css/board.css">
	<script src="Action/js/board.js"></script>

	<div class="boardpage">
		<table id="readtable" class="maintable">
			<caption>게시글 보기</caption>
			<thead>
				<tr>
					<th>제 목</th>
					<td class="title" colspan="5">${freeBoard.title}</td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td class="writer">${freeBoard.memberID}</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="contents" colspan="6">${freeBoard.contents}</td>
				</tr>
			</tbody>
		</table>
		<div class="buttonbar">
			<input type="button" value="목록" onclick="goUrl('/Action/AuctionBoard?action=list&pageNumber=${currentPageNumber}&boardNum=${auctionBoard.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}');">
			<c:if test = "${not empty sessionScope.loginMember}">
				<input type="button" value="답글" onclick="goUrl('replyForm?pageNumber=${currentPageNumber}&num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}');">
			</c:if>
			<c:if test = "${sessionScope.loginMember.memberID eq board.writer or sessionScope.loginMember.memberID eq 'duke'}">
				<input type="button" value="수정" onclick="goUrl('/Action/AuctionBoard?action=update&pageNumber=${currentPageNumber}&boardNum=${auctionBoard.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}');">
				<input type="button" value="삭제" onclick="deleteCheck('/Action/AuctionBoard?action=remove&pageNumber=${currentPageNumber}&boardNum=${auctionBoard.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}');">
			</c:if>
		</div>
	</div>

