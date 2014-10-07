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
					<td class="memberID">${freeBoard.memberID}</td>
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
				<input type="button" value="수정" onclick="goUrl('/Action/AuctionBoard?action=update&pageNumber=${currentPageNumber}&boardNum=${auctionBoard.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}');">
				<input type="button" value="삭제" onclick="deleteCheck('/Action/AuctionBoard?action=remove&pageNumber=${currentPageNumber}&boardNum=${freeBoard.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}');">
			
		</div>
	</div>

