<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <c:if test="${not empty requestScope.loginErrorMsg}">
 <ul id="loginerrormsg">
     <li>${requestScope.loginErrorMsg}</li>
 </ul>
 </c:if>    
<form action="/Action/MemberController?action=login" method="POST">
    <table id="logintable">
        <tr>
            <td class="label">아이디</td>
            <td><input type="text" name="memberID" size="10"></td>
            <td rowspan="2"><input type="submit" name="login" value="로그인"></td>
        </tr>
        <tr>
            <td class="label">패스워드</td>
            <td><input type="password" name="password" size="10"></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="2"><label class="memberFont"><a href="<c:url value='/views/member/registerMember.jsp' />">회원가입</a></label></td>
        </tr>
        <tr>    
            <td colspan="2"><label class="memberFont"><a href="">아이디/비밀번호찾기</a></label></td>
            
        </tr>
    </table>
</form>