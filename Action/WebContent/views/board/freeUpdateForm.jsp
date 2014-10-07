<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 수정</title>
	<link rel="stylesheet" href="Action/css/board.css">
	<script src="/Action/ckeditor/ckeditor.js"></script>
	<script src="/Action/js/board.js"></script>
</head>
<body>
	<div class="boardpage">
		<form name="writeForm" action="/Action/FreeBoard?action=update&pageNumber=${currentPageNumber}&boardNum=${auctionBoard.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}" method="POST">
			<table id="updatetable" class="maintable">
				<caption>게시글 수정</caption>
				<thead>
					<tr>
						<th>제 목</th>
						<td><input class="titleinput" type="text" name="title" maxlength="100" value="${freeBoard.title}"><input class="checkbox" type="checkbox" name ="isNotice" value="1"><label calss="label">공지사항 체크</label></td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><input class="writerinput" type="text" name="memberID" maxlength="20" value="${freeBoard.memberID}" readonly="readonly"></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">
							<textarea class="contentsinput ckeditor" name="contents">
								<script>
									CKEDITOR.replace('contents');
								</script>
									${freeBoard.contents}
							</textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="buttonbar">
				<input type="button" value="수정" onclick="boardWriteCheck(this.form)">
				<input type="button" value="취소" onclick="goUrl('/Action/FreeBoard?action=read&pageNumber=${currentPageNumber}&boardNum=${auctionBoard.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}');">
			</div>
		</form>
	</div>
</body>
</html>
