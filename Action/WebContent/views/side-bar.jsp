<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="sidebar">
	<br>
    <c:if test="${empty sessionScope.loginMember}">
        <c:import url="/views/member/login.jsp" />
    </c:if>
    <c:if test="${not empty sessionScope.loginMember}">     
        <c:import url="/views/member/logout.jsp" />        
    </c:if>
    
    <table id="sidebartable">
        <tr>
            <td id="menulabel">Menu</td>
        </tr>    
        <tr>
            <td><hr></td>
        </tr>
        <tr>
            <td><a href="/Action/views/board/auctionList.jsp">경매상품보기</a></td>
        </tr>
        <tr>
            <td><a href="">자유게시판</a></td>
        </tr>
        <tr>
            <td><a href="">Q&A</a></td>
        </tr>
    </table>
</div>

	
