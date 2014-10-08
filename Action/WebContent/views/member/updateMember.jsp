<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Action/action.css">
<link rel="stylesheet" href="/Action/auction.css">
<script src="/Action/js/member.js"></script>
<title>Action Update</title>
</head>
<body>
     <div class="tableContainer">
        <div class="tableRow">
            <c:import url="/views/banner.jsp"/>
        </div>
        <div class="tableRow">
            <div class="datagrid">
	            <form action="/Action/MemberController?action=update" method="POST">
	            	<table>
	            		<tr>
	            			<td><label class="registerLabel" >회원가입</label></td>
	            		</tr>
	            		<tr>
	            			<td class="td"><label class="label">회원 아이디</label></td>
	            			<td class="td"><label>${sessionScope.loginMember.memberID}</label></td>
	            		</tr>       	
	            		<tr>
	            		    <td class="tdbg"><label class="label">회원 이름</label></td>
	            			<td class="tdbg"><input type="text" name="name" value="${sessionScope.loginMember.name}"></td>
	            		</tr>
	            		<tr>
	            		    <td class="td"><label class="label">비밀번호</label></td>
	            			<td class="td"><input type="password" name="password" value="${sessionScope.loginMember.password}"></td>
	            		</tr>
	            		
						<tr>
	            		    <td class="tdbg"><label class="label">연락처</label></td>
	            			<td class="tdbg"><input type="text" name="tel" value="${sessionScope.loginMember.tel}"></td>
	            		</tr>
	            		<tr>
	            		    <td class="td"><label class="label">주소</label></td>
	            			<td class="td"><input type="text" name="address" value="${sessionScope.loginMember.address}"></td>
	            		</tr>
	            		<tr>
	            		    <td><input type="submit" name="update" class="button" value="수정"></td>
	            			<td><input type="button" name="cancle" class="button" value="취소" onclick="goUrl('/Action/views/member/infomationMember.jsp');"></td>
	            		</tr>
	            		
	            	</table>
	            </form>
        	</div>
        </div>
        
    <!--     <div class="tableCell">
        	<img alt="" src="../images/registerimg.gif">
        </div>
         -->
        <div class="tableRow">

            <div class="tableCell">
               <c:import url="/views/foter.jsp"/>
            </div>
        </div>
    </div>
</body>
</html>
	