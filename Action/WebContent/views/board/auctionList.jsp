<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Action/action.css">
<link rel="stylesheet" href="/Action/List.css">
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
            <div class="datagrid">
            	<table id="allauctionlist">
            	<thead>
            		<tr><th>경매번호</th>
            			<th>이미지</th>
            			<th>경매물품</th>
            			<th>현재입찰금액</th>
            			<th>즉시구매가격</th>
            			<th>등록자</th>
            			<th>종료일</th>
            		</tr>
            	</thead>
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
                                <img src="img/tmp/th_${auction.mainImage}">
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
						<td id="pagenavigator" colspan="7">
						<div id="paging">
						<ul>
							<c:if test="${startPageNumber > 1}">
								<li><a href="/Action/AuctionBoard?action=list&pageNumber=${startPageNumber - 1}&searchType=${param.searchType}&searchText=${param.searchText}"><span>이전</span></a></li>
							</c:if>
							<c:forEach var="pageNumber" begin ="${requestScope.startPageNumber}" end="${requestScope.endPageNumber}">
								<c:choose>
									<c:when test="${pageNumber eq currentPageNumber}">
										<li><a class="pagenumber currpage"><span>${pageNumber}</span></a></li>
									</c:when>
									<c:otherwise>
										<li><a class="pagenumber" href="/Action/AuctionBoard?action=list&pageNumber=${pageNumber}&searchType=${param.searchType}&searchText=${param.searchText}"><span>${pageNumber}</span></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${endPageNumber < totalPageCount}">
								<li><a href="/Action/AuctionBoard?action=list&pageNumber=${endPageNumber + 1}&searchType=${param.searchType}&searchText=${param.searchText}"><span>다음</span></a></li>
							</c:if>
							</ul>	
							
							</div>
								<tr>
								<!-- 공백용 td -->
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							<td align ="right">
								<c:choose>
									<c:when test="${empty sessionScope.loginMember}">
										<input type="button" class = "button" value="경매등록" onclick="writeCheck('<c:url value="/views/member/registerMember.jsp"/>');">
									</c:when>
									<c:otherwise>
										<input type="button" class = "button" value="경매등록" onclick="goUrl('/Action/AuctionBoard?action=writeForm');">
									</c:otherwise>
								</c:choose>
							</td>	
					</tr>
						</td>	
					</tr>
				</tfoot>
				<tr>	
					
					
					
					
				</tr>
           	</table>
       	</div>
	       		</div>
        </div>

        <div class="tableRow">

            <div class="tableCell">
               <c:import url="/views/foter.jsp"/>
            </div>
        </div> 

</body>
</html>
	