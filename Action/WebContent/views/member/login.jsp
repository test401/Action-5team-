<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="" method="POST">
    <table id="logintable">
        <tr>
            <td class="label">아이디</td>
            <td><input type="text" name="" size="10"></td>
            <td rowspan="2"><input type="submit" name="login" value="로그인"></td>
        </tr>
        <tr>
            <td class="label">패스워드</td>
            <td><input type="password" name="" size="10"></td>
            <td></td>
        </tr>
        <tr>
            <td><label style="font-size: x-small;"><a href="">회원가입</a></label></td>
            <td><label style="font-size: x-small;" ><a href="">아이디/비밀번호찾기</a></label></td>
            <td></td>
        </tr>
    </table>
</form>