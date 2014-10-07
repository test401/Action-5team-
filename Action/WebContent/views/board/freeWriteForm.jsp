<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 등록</title>
	<link rel="stylesheet" href="Action/css/board.css">
	<script src="/Action/ckeditor/ckeditor.js"></script>
	<script src="/Action/js/board.js"></script>
</head>
<body>
	<div class="boardpage">
		<form name="writeForm" action="/Action/FreeBoard?action=write" method="POST"">
			<table id="writetable" class="maintable">
				<caption>게시글 입력</caption>
				<thead>
					<tr>
						<th>제 목</th>
						
						<td><input class="titleinput" type="text" name="title" maxlength="100"><input class="checkbox" type="checkbox" name ="isNotice" value="1"><label calss="label">공지사항 체크</label></td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><input class="writerinput" value="${loginMember.memberID}" type="text" name="writer" maxlength="20" onblur="checkNotEmpty(this, document.getElementById('writerError'));"><span id= "writerError"></span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">
							<textarea class="contentsinput" name="contents"></textarea>
							<script>
								CKEDITOR.replace('contents');
							</script>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="buttonbar">
				<input type="button" value="등록" onclick="boardWriteCheck(this.form);">
				<input type="button" value="취소" onclick="goUrl('/Action/FreeBoard?action=list');">
			</div>
		</form>
	</div>
</body>
</html>
