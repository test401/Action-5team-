<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Action/action.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="/Action/ckeditor/ckeditor.js"></script>
<script src="/Action/js/board.js"></script>

<title>Action Write Form</title>
</head>
<body>
     <div class="tableContainer">
        <div class="tableRow">
            <c:import url="/views/banner.jsp"/>
        </div>
        <div class="tableRow">
	        <div class="tableCell">
	        	<c:import url="/views/side-bar.jsp" />
	            <div class="main">
	            	<form name = "form" action="/Action/AuctionBoard?action=write" method="POST" enctype="multipart/form-data">
		            	<table>
		            		<tr>
		            			<td><label class="registerlabel" >경매물품등록</label></td>
		            		</tr>
		            		<tr>
		            			<td><label class="label">카테고리</label></td>
		            			<td>
		            				<select name="categoryID">
		            					<option value="1">카</option>
		            					<option value="2">테</option>
		            					<option value="3">고</option>
		            					<option value="4">리</option>
		            				</select>
		            			</td>
		            		</tr>
		            		<tr>
		            		    <td><label class="label">제목</label></td>
		            			<td><input type="text" name="title" placeholder="글제목을 입력하여주세요."></td>
		            		</tr>
		            		<tr>
		            		    <td><label class="label">대표이미지</label></td>
		            			<td><input type="file" name="mainImage"></td>
		            		</tr>
		            		<tr>
		            		    <td><label class="label">시작가</label></td>
		            			<td><input type="text" name="startPrice"></td>
		            		</tr>
							<tr>
		            		    <td><label class="label">즉시구매가</label></td>
		            			<td><input type="text" name="immediatelyPrice" ></td>
		            			<td><label class="label">즉시구매허용여부</label>
		            			<input type="checkbox" name="isImmediately" onclick="checkImm(this.form)" value="1" checked="checked">
		            			</td>
		            		</tr>
		            		<tr>
		            		    <td><label class="label">기간</label></td>
		            			<td><input type="radio" name="endTime" value="3">3일<input type="radio" name="endTime" value="5">5일<input type="radio" name="endTime" value="7">7일</td>
		            		</tr>
		            		<tr>
		            		    <td><label class="label">업로드이미지</label></td>
		            			<td><input type="file" id="attachFile" name="image"></td>
		            			<td><input id="uploadimagefile" type="button" value="업로드"></td>
		            		</tr>
		            		<tr>
		            			<td colspan="10">
		            				<textarea rows="10" cols="80" name="contents"></textarea>
		            		<script>
							 	CKEDITOR.replace('contents',{enterMode:'2', shiftEnterMode:'3'});
							</script>
		            			</td>
		            		</tr>
		            		<tr>
		            		    <td><input type="button" name="register" value="경매등록" onclick="boardWriteCheck(this.form);"></td>
		            			<td><input type="button" name="cancle" value="취소" onclick="goUrl('/Action/AuctionBoard?action=list&pageNumber=${currentPageNumber}&boardNum=${auction.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&categoryType=${param.categoryType}');"></td>
		            		</tr>
		            	</table>
		            </form>
	        	</div>
		    </div>
        </div>

        <div class="tableRow">

            <div class="tableCell">
               <c:import url="/views/foter.jsp"/>
            </div>
        </div>
    </div>
</body>
</html>
	