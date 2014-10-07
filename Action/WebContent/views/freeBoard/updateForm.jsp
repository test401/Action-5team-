<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 수정</title>
	<link rel="stylesheet" href="../css/board.css">
	<script src="../ckeditor/ckeditor.js"></script>
	<script src="../js/board.js"></script>
</head>
<body>
	<div class="boardpage">
		<form name="writeForm" action="update?pageNumber=${currentPageNumber}&num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}" method="POST">
			<table id="updatetable" class="maintable">
				<caption>게시글 수정</caption>
				<thead>
					<tr>
						<th>제 목</th>
						<td><input class="titleinput" type="text" name="title" maxlength="100" value="${board.title}"></td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><input class="writerinput" type="text" name="writer" maxlength="20" value="${board.writer}"></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">
							<textarea class="contentsinput" name="contents">${board.contents}</textarea>
							<script>
								CKEDITOR.replace('contents')
							</script>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="buttonbar">
				<input type="button" value="수정" onclick="boardWriteCheck(this.form);">
				<input type="button" value="취소" onclick="goUrl('read?pageNumber=${currentPageNumber}&num=${board.num}');">
			</div>
		</form>
	</div>
</body>
</html>
