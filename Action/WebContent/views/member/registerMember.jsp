<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Register Member</title>
</head>
<body>
     <div class="tableContainer">
        <div class="tableRow">
            <c:import url="/views/banner.jsp"/>
        </div>
        <div class="tableRow">
            <c:import url="/views/side-bar.jsp" />
            <div class="main">
	            <form action="" method="POST">
	            	<table>
	            		<tr>
	            			<td><label class="registerLabel" >회원가입</label></td>
	            		</tr>
	            		<tr>
	            			<td><label class="label">회원 아이디</label></td>
	            			<td><input type="text" name="id"></td>
	            		</tr>       	
	            		<tr>
	            		    <td><label class="label">회원 이름</label></td>
	            			<td><input type="text" name="name"></td>
	            		</tr>
	            		<tr>
	            		    <td><label class="label">비밀번호</label></td>
	            			<td><input type="text" name="password"></td>
	            		</tr>
	            		<tr>
	            		    <td><label class="label">비밀번호확인</label></td>
	            			<td><input type="text" name="checkPassowrd"></td>
	            		</tr>
						<tr>
	            		    <td><label class="label">연락처</label></td>
	            			<td><input type="text" name="tel"></td>
	            		</tr>
	            		<tr>
	            		    <td><label class="label">주소</label></td>
	            			<td><input type="text" name="address"></td>
	            		</tr>
	            		<tr>
	            		    <td><input type="submit" name="register" value="가입신청"></td>
	            			<td><input type="button" name="cancle" value="취소"></td>
	            		</tr>
	            		
	            	</table>
	            </form>
        	</div>
        </div>
        
        <div class="tableCell">
        	<img alt="" src="../images/registerimg.gif">
        </div>
        
        <div class="tableRow">

            <div class="tableCell">
               <c:import url="/views/foter.jsp"/>
            </div>
        </div>
    </div>
</body>
</html>
	