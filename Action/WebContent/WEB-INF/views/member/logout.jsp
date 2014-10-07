<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="" method="POST">
    <table id="logouttable">
        <tr>
            <td class="message">${sessionScope.loginMember.name} 님<br> 환영합니다.</td>
            <td><input type="submit" name="logout" value="로그아웃"></td>
        </tr>
        <tr>
            <td><a href="">내정보</a></td>
            <td><a href="">입찰리스트</a></td>
        </tr>
    </table>
</form>