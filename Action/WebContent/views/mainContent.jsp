<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


        <div class="tableRow">
			<c:import url="/views/category.jsp"></c:import>
		</div>
		<div class="tableRow">
			<c:import url="/views/searchbar.jsp"></c:import>
		</div>
		<div class="tableRow">
			<div class="main">
				<table id = "topauctionlist">
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
                        <td class="auctionimmediatelyPrice">${auction.immediatelyPrice}</td>
                        <td class="auctionmemberID">${auction.memberID}</td>
                        <td class="auctionendTime">${auction.endTime}</td>
                    </tr>
                    </c:forEach>
                </table>
            	<table id="allauctionlist">
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
										<a class="pagenumber" href="list?pageNumber=${pageNumber}&searchType=${param.searchType}&searchText=${param.searchText}">${pageNumber}</a>
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
