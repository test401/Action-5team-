<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../action.css">
<title>Action List</title>
</head>
<body>
     <div class="tableContainer">
        <div class="tableRow">
            <c:import url="/views/banner.jsp"/>
        </div>
        <div class="tableRow">
        <div class="tableCell">
        		<c:import url="/views/side-bar.jsp" />

        	
            <div class="main">
            	<table id="allauctionlist">
            		<tr>
            			<td id = listHeaderLift><a>경매마감순</a><a>신규등록순</a></td>
            			<td id = listHeaderRight>
            				<c:if test='${not empty sessionScope.loginMember}'>
                				<form id="writeAuctionForm" action="">
	                				<input type="submit" value="경매등록">
	                			</form>
                			</c:if>
                		</td>
            		</tr>
					<c:forEach items='${requestScope["AuctionList"]}' var="auction" varStatus="loopStatus">
                    <tr>
                        <td class="auctionno">${auction.boardNum}</td>
                        <td class="auctionimage">
                            <a href="">
                                <img src="images/${auction.image}">
                            </a>
                        </td>
                        <td class="auctiontitle">
                            <a href="">${auction.title}</a>
                        </td>
                        <td class="auctioncurrentPrice">${auction.currentPrice}</td>
                        <td class="auctionimmediatelyPrice">${auction.immediatelyPrice}</td>
                        <td class="auctionmemberID">${auction.memberID}</td>
                        <td class="auctionendTime">${auction.endTime}</td>
                    </tr>
                    </c:forEach>
            	</table>
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
	