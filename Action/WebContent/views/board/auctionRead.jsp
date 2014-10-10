<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Action/action.css">
<link rel="stylesheet" href="/Action/auction.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="/Action/js/auction.js"></script>
<script src="/Action/js/board.js"></script>
<script>
// 현재 시각을 표시하는 함수 선언
function displayTime() {
	var elt = document.getElementById("clock"); // id="clock"인 요소 찾기
	var time = document.getElementById("endTime");
	var currentPriceold = document.getElementById("currentPriceold");
	var immediatelyPrice = document.getElementById("immediatelyPrice");
	
	var now = new Date(); // 현재 시각 얻기
	var date = new Date(time.value);//마감날짜
	date.setHours(24, 0, 0, 0);//마감시간
	var endTime = parseInt(date.getTime()/1000);//마감시간 초로만들기
	var nowTime = parseInt(now.getTime()/1000);//현재시간 초로만들기
	var diff = parseInt(endTime-nowTime);//시간차 계산


	//시간을 구하기 위한 일수 계산
	dayTemp= Math.floor((diff/86400));
	//시간을 구함
	hourDisplay=Math.floor((diff)/3600);
	hour=Math.floor((diff-dayTemp*86400)/3600);
	//분을 구함
	minute=Math.floor(((diff-dayTemp*86400)-(hour*3600))/60);
	//초를 구함
	second=diff%60;
	
	var id=setTimeout(displayTime, 1000); // 1초 후에 재 실행
	//표시
	elt.innerHTML = "종료시간 : "+hourDisplay+":"+minute+":"+second;

	
	if(immediatelyPrice.value != 0){
		if(currentPriceold.value >= immediatelyPrice.value){
			diff=0;
		}
	}
          //만약 남은 시간이 0이하이면 종료
	if (diff <= 0) {
          	clearTimeout(id);
          	document.getElementById("bid").setAttribute("disabled","disabled");
          	elt.innerHTML = "마감";
          }
}
window.onload = displayTime; // 문서가 로딩될 때 수행할 함수 설정
</script>

<title>Action</title>
</head>
<body>
     <div class="tableContainer">
         <div class="tableRow">
            <c:import url="/views/banner.jsp"/>
        </div>
        <div class="tableRow">
	        <div class="tableCell">
	
	        	<div class="tableCell">
	            	<img alt="" src="">
	            </div>  
	            
	            <div class="datagrid" style="margin-left: 180px;">
	            	<table class="border">
	            		<tr>
	            			<td><label><font class="Three-Dee">경매물품</font></label></td>
	            			
	            			<td colspan="3">${auctionBoard.title}</td>
	            		</tr>
	            		<tr>
	            			<td align = "center" rowspan = "9"><img src="/Action/img/${auctionBoard.mainImage}" style="width: 300px; height: 300px;"></td>
	            		</tr>       	
	            		<tr>
	            		    <th><label class="label">현재입찰가</label></th>
	            			<td id="retruncurrentPrice">${auctionBoard.currentPrice}</td>
	            			<td><textarea hidden="true" rows="" cols="" id="currentPriceold">${auctionBoard.currentPrice}</textarea></td>
	            			
	            		</tr>
	            		<tr>
	            		    <th><label class="label">시작가</label></th>
	            			<td class = "alt">${auctionBoard.startPrice}</td>
	            		</tr>
						<tr>
	            		    <th><label class="label">즉시구매가</label></th>
	            			<td>${auctionBoard.immediatelyPrice}</td>
	            			<td><textarea hidden="true" rows="" cols="" id="immediatelyPrice">${auctionBoard.immediatelyPrice}</textarea></td>
	            		</tr>
	            		<tr>
	            		    <th><label class="label">입찰수</label></th>
	            			<td></td>
	            		</tr>
	            		<tr>
	            		    <th><label class="label">시작날짜</label></th>
	            			<td colspan="4">${auctionBoard.startTime}</td>
	            		</tr>
	            		<tr>
	            		    <th><label class="label">마감날짜</label></th>
	            			<td colspan="4"><textarea hidden="true" rows="" cols="" id="endTime">${auctionBoard.endTime}</textarea>${auctionBoard.endTime}</td>
	            		</tr>
	            		<tr>
	            		    <th><label class="label">남은시간</label></th>
	            			<td colspan="4"><span id="clock" ></span></td>
	            		</tr>
	            		
	            		<tr>
	            		    <th><label class="label">입찰가</label></th>
	            			<td>
	            				<form action="/Action/AuctionBoard?action=bid" method="get">
            						<input value="${auctionBoard.currentPrice}" type="number" step="100" name="currentPrice" min="${auctionBoard.currentPrice}" max ="${auctionBoard.immediatelyPrice}" autofocus="autofocus">
	            					<c:if test="${not empty loginMember && loginMember.memberID != auctionBoard.memberID}">
	            						<input hidden="true" type="text" name="boardNum" value="${auctionBoard.boardNum}">
		            					<td colspan="2"><input id="bid" class = "button" type="button" value="입찰하기"></td>
		            					<!-- <input type="button" value="입찰취소" onclick=""> -->
									</c:if>
	            				</form>
	            				<%-- <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nyear"/> --%>
	            			</td>
	            		</tr>
	            		<tr>
							<td name ="content" class="td" colspan="8">${auctionBoard.contents}test</td>
	            		</tr>
	            		<tr>
	            		
	            		<td><!-- 공백용 TD --></td>
	            			<c:if test="${auctionBoard.memberID == loginMember.memberID}">
		            			<td><input type="button" class = "button" name="update" value="수정" onclick="goUrl('/Action/AuctionBoard?action=updateForm&pageNumber=${currentPageNumber}&boardNum=${auctionBoard.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&categoryType=${param.categoryType}')"></td>
		            			<td><input type="button" class = "button" name="delete" value="삭제" onclick="deleteCheck('/Action/AuctionBoard?action=remove&boardNum=${auctionBoard.boardNum}')"></td>	
		            			<td><input hidden="true" type="text" name="bidmemberID" value="${loginMember.memberID}"></td>
	            			</c:if>
		            			<td><input type="button" class = "button" value="목록" onclick="goUrl('/Action/AuctionBoard?action=list&pageNumber=${currentPageNumber}&boardNum=${auction.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&categoryType=${param.categoryType}');"></td>
	            		</tr>
	            	</table>
	        	</div>
	        	<div>
	        		<%-- <c:import url="/views/board/auctionReplyForm.jsp" />   --%>
	        	</div>
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
	