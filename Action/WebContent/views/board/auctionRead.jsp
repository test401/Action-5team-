<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../action.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../../js/auction.js"></script>
<script src="../../js/board.js"></script>
<script>
// 현재 시각을 표시하는 함수 선언
function displayTime() {
	var elt = document.getElementById("clock"); // id="clock"인 요소 찾기
	var time = document.getElementById("endTime");
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
          //만약 남은 시간이 0이하이면 종료
	if (diff <= 0) {
          	clearTimeout(id);
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
	        	<c:import url="/views/side-bar.jsp" />
	        	<div class="tableCell">
	            	<img alt="" src="">
	            </div>
	            <div class="main">
	            	<table>
	            		<tr>
	            			<td><label class="registerlabel" >경매물품</label></td>
	            			<c:if test="${auctionBoard.memberID == loginMember.memberID}">
		            			<td><input type="button" name="update" value="수정"></td>
		            			<td><input type="button" name="delete" value="삭제"></td>
	            			</c:if>
	            		</tr>
	            		<tr>
	            			<td colspan="2">${auctionBoard.title}</td>
	            			<td></td>
	            		</tr>       	
	            		<tr>
	            		    <td><label class="label">현재입찰가</label></td>
	            			<td>${auctionBoard.currentPrice}</td>
	            		</tr>
	            		<tr>
	            		    <td><label class="label">시작가</label></td>
	            			<td>${auctionBoard.startPrice}</td>
	            		</tr>
						<tr>
	            		    <td><label class="label">즉시구매가</label></td>
	            			<td>${auctionBoard.immediatelyPrice}</td>            	
	            		</tr>
	            		<tr>
	            		    <td><label class="label">입찰수</label></td>
	            			<td></td>
	            		</tr>
	            		<tr>
	            		    <td><label class="label">시작날짜</label></td>
	            			<td colspan="4">${auctionBoard.startTime}</td>
	            		</tr>
	            		<tr>
	            		    <td><label class="label">마감날짜</label></td>
	            			<td colspan="4"><textarea hidden="true" rows="" cols="" id="endTime">2014-10-02</textarea>2014-10-02${auctionBoard.endTime}</td>
	            		</tr>
	            		<tr>
	            		    <td><label class="label">남은시간</label></td>
	            			<td colspan="4"><span id="clock" ></span></td>
	            		</tr>
	            		<tr>
	            		    <td><label class="label">입찰가</label></td>
	            			<td colspan="7">
	            				<form action="" method="get">
	            					<input type="number" step="100" name="currentPrice" min="${auctionBoard.currentPrice}" autofocus="autofocus">
	            					<%-- <c:if test="${auctionBoard.endTime >= now }"> --%>
	            						<input hidden="true" type="text" name="boardNum" value="${auctionBoard.boardNum}">
		            					<input type="button" value="입찰하기" onclick="">
		            					<input type="button" value="입찰취소" onclick="">
									<%-- </c:if> --%>
	            				</form>
	            				<%-- <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nyear"/> --%>
	            			</td>
	            		</tr>
	            		<tr>
							<td class="contents" colspan="8">${auctionBoard.contents}</td>
	            		</tr>
	            	</table>
	        	</div>
	        	<div>
	        		<%-- <c:import url="/views/board/auctionReplyForm.jsp" /> --%>
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
	