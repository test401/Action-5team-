<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Edit Form</title>
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
	            	<form action="" method="POST">
		            	<table>
		            		<tr>
		            			<td><label class="updatelabel" >경매물품수정</label></td>
		            		</tr>
		            		<tr>
		            			<td><label class="label">카테고리</label></td>
		            			<td>
		            				<select name="categoryID">
		            					<option value=""></option>
		            					<option value=""></option>
		            					<option value=""></option>
		            					<option value=""></option>
		            				</select>
		            			</td>
		            		</tr>       	
		            		<tr>
		            		    <td><label class="label">제목</label></td>
		            			<td><input type="text" name="title" placeholder="글제목을 입력하여주세요." value="${auctionBoard.title}"></td>
		            		</tr>
		            		<tr>
		            		    <td><label class="label">대표이미지</label></td>
		            			<td><input type="text" name="image"></td>
		            		</tr>
		            		<tr>
		            		    <td><label class="label">시작가</label></td>
		            			<td><input type="text" name="startPrice">${auctionBoard.startPrice}</td>
		            		</tr>
							<tr>
		            		    <td><label class="label">즉시구매가</label></td>
		            			<td><input type="text" name="immediatelyPrice" value="${auctionBoard.immediatelyPrice}"></td>
		            			<td><label class="label">즉시구매허용여부</label>
		            				<input type="checkbox" name="isImmediately" value="1" 
		            				<c:if test="${auctionBoard.isImmediately == 1}">checked="checked"</c:if>>
		            			</td>
		            		</tr>
		            		<tr>
		            		    <td><label class="label">기간</label></td>
		            			<td>
			            			<input type="radio" name="endTime" value="3" >
			            			<input type="radio" name="endTime" value="5">
			            			<input type="radio" name="endTime" value="7">
		            			</td>
		            		</tr>
		            		<tr>
		            			<td>
		            				<textarea rows="" cols="" name="contents">value="${auctionBoard.contents}"</textarea>
		            			</td>
		            		</tr>
		            		<tr>
		            		    <td><input type="submit" name="register" value="가입신청"></td>
		            			<td><input type="button" name="cancle" value="취소"></td>
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
	