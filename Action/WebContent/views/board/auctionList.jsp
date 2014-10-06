<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Action/action.css">
<script src="/Action/js/board.js">
</script>
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
        <div class="tableCell">
				<c:import url="/views/category.jsp"></c:import>
		</div>
		<div class="buttonbar">
				<c:import url="/views/searchbar.jsp"></c:import>
		</div>
		
		
		<!-- 여기부터 시작 경매상품 보기 -->
            <div class="main">
            	<table id="allauctionlist">
            		<tr>
            			<td id = listHeaderLift><a>경매마감순</a><a>신규등록순</a></td>
            			<td id = listHeaderRight>         				
						<c:choose>
							<c:when test="${empty sessionScope.loginMember}">
								<input type="button" value="경매등록" onclick="writeCheck('<c:url value="/views/member/registerMember.jsp"/>');">
							</c:when>
							<c:otherwise>
								<input type="button" value="경매등록" onclick="goUrl('/Action/AuctionBoard?action=writeForm');">
							</c:otherwise>
						</c:choose>
                		</td>
            		</tr>
            		<c:choose>
					<c:when test="${empty requestScope.auctionList}">
						<tr>
							<td colspan="5">등록된 게시물이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach items='${requestScope["auctionList"]}' var="auction" varStatus="loopStatus">
                    <tr>
                        <td class="auctionno">번호 : ${auction.boardNum}</td>
                        <td class="auctionimage">
                            <a href="">
                                <img src="images/${auction.image}">
                            </a>
                        </td>
                        <td class="auctiontitle">
                            <a href="/Action/AuctionBoard?action=read&pageNumber=${currentPageNumber}&boardNum=${auction.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&categoryType=${param.categoryType}">${auction.title}</a>
                        </td>
                        <td class="auctioncurrentPrice">${auction.currentPrice}</td>
                        <td class="auctionimmediatelyPrice">${auction.immediatelyPrice}</td>
                        <td class="auctionmemberID">${auction.memberID}</td>
                        <td class="auctionendTime">${auction.endTime}</td>
                    </tr>
                    </c:forEach>
                    </c:otherwise>
				</c:choose>
				<tfoot>
					<tr>
						<td id="pagenavigator" colspan="5">
							<c:if test="${startPageNumber > 1}">
								<a href="list?pageNumber=${startPageNumber - 1}&searchType=${param.searchType}&searchText=${param.searchText}">이전</a>
							</c:if>
							<c:forEach var="pageNumber" begin ="${requestScope.startPageNumber}" end="${requestScope.endPageNumber}">
								<c:choose>
									<c:when test="${pageNumber eq currentPageNumber}">
										<a class="pagenumber currpage">${pageNumber}</a>
									</c:when>
									<c:otherwise>
										<a class="pagenumber" href="/Action/AuctionBoard?action=list&pageNumber=${pageNumber}&searchType=${param.searchType}&searchText=${param.searchText}">${pageNumber}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${endPageNumber < totalPageCount}">
								<a href="list?pageNumber=${endPageNumber + 1}&searchType=${param.searchType}&searchText=${param.searchText}">다음</a>
							</c:if>
						</td>
					</tr>
				</tfoot>
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
	