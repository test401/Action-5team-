<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="/Action/js/login.js"></script>
<div class="sidebar">
	<div id ="sidebarMargin">
		<%-- <c:if test="${empty sessionScope.loginMember}">
			<c:import url="/views/member/login.jsp" />
		</c:if>
		<c:if test="${not empty sessionScope.loginMember}">
			<c:import url="/views/member/logout.jsp" />
		</c:if> --%>
		
		<img id="actionTitle" src="/Action/views/images/actionTitle.gif"
		alt="액션타이틀">
		<embed id="actionTitle" autoplay="true" autostart="true">
	</div>
    
    
    
    <table id="sidebartable">
        <tr>
            <td id="menulabel">Menu</td>
        </tr>    
        <tr><td><hr></td>
        </tr>
        <tr>
            <td><a href="/Action/AuctionBoard?action=list">경매상품보기</a></td>
        </tr>
        <tr>
            <td><a href="">자유게시판</a></td>
        </tr>
        <tr>
            <td><a href="">Q&A</a></td>
        </tr>
    </table>
</div>

	
