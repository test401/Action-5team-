<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Action/action.css">
<script src="/Action/js/member.js"></script>
<title>Action My Information</title>
</head>
<body>
     <div class="tableContainer">
        <div class="tableRow">
            <c:import url="/views/banner.jsp"/>
        </div>
        <div class="tableRow">
            <c:import url="/views/side-bar.jsp" />
            <div class="main">
	            	<table>
	            		<tr>
	            			<td><label class="registerLabel" >회원가입</label></td>
	            		</tr>
	            		<tr>
	            			<td><label class="label">회원 아이디</label></td>
	            			<td><label>${sessionScope.loginMember.memberID}</label></td>
	            		</tr>       	
	            		<tr>
	            		    <td><label class="label">회원 이름</label></td>
	            			<td><label class="label">${sessionScope.loginMember.name}</label></td>
	            		</tr>
						<tr>
	            		    <td><label class="label">연락처</label></td>
	            			<td><label class="label">${sessionScope.loginMember.tel}</label></td>
	            		</tr>
	            		<tr>
	            		    <td><label class="label">주소</label></td>
	            			<td><label class="label">${sessionScope.loginMember.address}</label></td>
	            		</tr>
	            		<tr>
	            		    <td><button name="update" onclick="goUrl('/Action/views/member/updateMember.jsp')">수정</button></td>
	            			<td><button name="delete" onclick="deleteCheck('/Action/MemberController?action=remove');">탈퇴</button></td>
	            		</tr>
	            		
	            	</table>
        	</div>
        </div>   
        
        <div class="tableRow">
			<div class="tableCell">
            </div>
            <div class="tableCell">
               <c:import url="/views/foter.jsp"/>
            </div>
        </div>
    </div>
</body>
</html>
	