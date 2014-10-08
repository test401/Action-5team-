<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="/Action/js/auction.js"></script>

        <div >
            	<table id="allauctionlist" style="margin-left: 100px;">
				<tfoot>
					<tr>
					<td style="width: 370px;">
						<p style="font-size: 17px;">최근등록된 상품</p>
					</td>
						<td id="pagenavigator" colspan="5">
							<c:forEach var="pageNumber" begin ="${requestScope.startPageNumber}" end="${requestScope.endPageNumber}">
								<c:choose>
									<c:when test="${pageNumber eq currentPageNumber}">
										<a class="pagenumber currpage">${pageNumber}</a>
									</c:when>
									<c:otherwise>
										<a class="pagenumber" href="/Action/AuctionBoard?action=main&pageNumber=${pageNumber}&searchType=${param.searchType}&searchText=${param.searchText}">${pageNumber}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</td>
					</tr>
				</tfoot>
            	</table>        
		</div>
			<div class="main" style="margin-left: 100px; text-align: center;">
					<c:forEach items='${requestScope["auctionList"]}' var="auction" varStatus="loopStatus">
					<div style="float: left; ">
						<table>
		                    <tr>
		                        <td class="auctionimage">
		                            <a href="/Action/AuctionBoard?action=read&pageNumber=${currentPageNumber}&boardNum=${auction.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&categoryType=${param.categoryType}">
		                                <c:choose>
		                                	<c:when test= "${empty auction.mainImage}">
		                                		<img src="images/noimage.png" style=" width: 150px; height: 150px;">
		                                	</c:when>
		                                	<c:otherwise>
			                                	<img src="img/tmp/th_${auction.mainImage}" style=" width: 150px; height: 150px;">
		                                	</c:otherwise>
		                                </c:choose>
		                            </a>
		                        </td>
		                   </tr>
		                   <tr>
		                        <td class="auctiontitle">
		                            <a href="/Action/AuctionBoard?action=read&pageNumber=${currentPageNumber}&boardNum=${auction.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&categoryType=${param.categoryType}">${auction.title}</a>
		                        </td>
		                   </tr>
		                   <tr>
		                        <td class="auctioncurrentPrice">현재 입찰가 : ${auction.currentPrice}</td>
		                   <tr>
		                        <td class="auctionendTime"><textarea hidden="true" rows="" cols="" name="endTime" class="endTime">${auction.endTime}</textarea>
		                        	${auction.endTime}까지
		                        </td>
		                    </tr>
	                    </table>
                    </div>
                    </c:forEach>
        	</div>