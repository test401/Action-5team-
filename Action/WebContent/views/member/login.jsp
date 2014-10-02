<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="loginck" action="/Action/MemberController?action=login" method="POST">
    <table id="logintable">
        <tr>
            <td class="loginlabel">아이디</td>
            <td><input type="text" name="loginmemberID" size="13"></td>
            <td rowspan="4"><input type="button" id="loginButton" name="login" value="로그인"></td>
        </tr>
        <tr>
            <td class="loginlabel">패스워드</td>
            <td><input type="password" name="loginpassword" size="13"></td>
            <td></td>
        </tr>
        <tr><td></td></tr>
        <tr><td></td></tr>
        <tr><td></td></tr>
        <tr>
            <td colspan="1"><label class="memberFont"><a href="<c:url value='/views/member/registerMember.jsp' />">회원가입</a></label></td> 
            <td colspan="2"><label class="memberFontID"><a href="">아이디/비밀번호찾기</a></label></td>
            
        </tr>
        <tr><td></td></tr>            
    </table>
</form>