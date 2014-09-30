<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../action.css">
<title>Action My Auction List</title>
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
	            			<td><label>${sessionScope.loginMember.id}</label></td>
	            		</tr>       	
	            		<tr>
	            		    <td><label class="label">회원 이름</label></td>
	            			<td><label class="label">${sessionScope.loginMember.name}"</label></td>
	            		</tr>
					<c:forEach items='${requestScope["AuctionList"]}' var="auction" varStatus="loopStatus">
                    <tr>
                        <td class="auctionno">${loopStatus.count}</td>
                        <td class="auctionimage">
                            <a href="">
                                <img src="images/${auction.image}">
                            </a>
                        </td>
                        <td class="auctiontitle">
                            <a href="">${auction.title}</a>
                        </td>
                        <td class="auctioncurrentPrice">${auction.currentPrice}</td>
                        <td class="auctionmemberID">${auction.memberID}</td>
                        <td class="auctionendTime">${auction.endTime}</td>
                    </tr>
                    </c:forEach>
	            	</table>
	            </form>
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
	