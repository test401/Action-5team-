<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Action/action.css">
<link rel="stylesheet" href="/Action/auction.css">
<script src="/Action/js/member.js"></script>
<title>Action Register Member</title>
</head>
<body>
     <div class="tableContainer">
        <div class="tableRow">
            <c:import url="/views/banner.jsp"/>
        </div>
        
        <div class="tableRow">
        	<div class="tableRow">
        		<div class="tableCell" style="width: 170px;">
        			
        		</div>
                <div class="datagrid" style="margin-left: 150px;">
	            	<form action="/Action/MemberController?action=register" method="POST">
	            	<table>
	            	<tbody>
	            		<tr>
	            			<td><label class="td" ><h1>회원가입</h1></label></td>
	            		</tr>
	            		<tr>
	            			<td class="td"><label class="label">회원 아이디</label></td>
	            			<td class="td"><input type="text" name="memberID" width="20px" size="25"></td>
	            		</tr>  
	            		
	            		
	            		     	
	            		<tr>
	            		    <td class="tdbg"><label class="label">회원 이름</label></td>
	            			<td class="tdbg"><input type="text" name="name" size="25"></td>
	            		</tr>
	            		
	            		
	            		<tr>
	            		    <td class="td"><label class="label">비밀번호</label></td>
	            			<td class="td"><input type="password" name="password" size="25"></td>
	            		</tr>
	
	            		<tr>
	            		    <td class="tdbg"><label class="label">비밀번호확인</label></td>
	            			<td class="tdbg"><input type="password" name="checkPassword" onblur="passwordCheck(this, document.getElementById('pass'));" size="25"><span id="pass"></span></td>
	            		</tr>
	            	
						<tr>
	            		    <td class="td"><label class="label">연락처</label></td>
	            			<td class="td"><input type="text" name="tel" size="25"></td>
	            		</tr>
	            	
	            		<tr>
	            		    <td class="tdbg"><label class="label">주소</label></td>
	            			<td class="tdbg"><input type="text" name="address" size="25"></td>
	            		</tr>
	            		</tbody>
	            		<tr>
	            			<td class="td"></td>
	            		    <td class="td" colspan="2"><input type="button" class="button" name="register" id="registerbtnin" value="가입신청" onclick="boardWriteCheck(this.form);" size="25">
	            			<input type="button" class="button" id="registerbtncancle" name="cancle" value="취소" onclick="goUrl('/Action/views/main.jsp');"></td>
	            		</tr>
	            	</table>
	            </form>
        	</div>
        		 <div class="tableCell" style="width: 50px;">

	       		</div>
        	
	            <div class="tableCell" style="margin-left: 320px;">
	        		<img id="registerimg" alt="" src=""
	        		style="width: 400px; height: 365px">
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
	