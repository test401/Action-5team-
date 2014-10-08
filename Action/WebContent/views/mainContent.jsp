<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="/Action/js/auction.js"></script>
<script>
// 현재 시각을 표시하는 함수 선언
/* function displayTime() {
	var time = $(".endTime").val();
	var now = new Date(); // 현재 시각 얻기
	var date = new Date(time);//마감날짜
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
	$(".clock").html("남은시간: "+hourDisplay+":"+minute+":"+second);

          //만약 남은 시간이 0이하이면 종료
	if (diff <= 0) {
          	clearTimeout(id);
          	$(".clock").html("마감");
          }
} */

function displayTime2(elt) {
	
	var clock = elt; // id="clock"인 요소 찾기
	var time = elt.form.endTime.value;
	alert(clock+"/"+time);
	var now = new Date(); // 현재 시각 얻기
	var date = new Date(time);//마감날짜
	date.setHours(24, 0, 0, 0);//마감시간
	var endTime = parseInt(date.getTime()/1000);//마감시간 초로만들기
	var nowTime = parseInt(now.getTime()/1000);//현재시간 초로만들기
	var diff = parseInt(endTime-nowTime);//시간차 계산
	alert(diff);
	//시간을 구하기 위한 일수 계산
	dayTemp= Math.floor((diff/86400));
	//시간을 구함
	hourDisplay=Math.floor((diff)/3600);
	hour=Math.floor((diff-dayTemp*86400)/3600);
	//분을 구함
	minute=Math.floor(((diff-dayTemp*86400)-(hour*3600))/60);
	//초를 구함
	second=diff%60;
	alert("종료시간 : "+hourDisplay+":"+minute+":"+second);
	var id=setTimeout(displayTime, 1000); // 1초 후에 재 실행
	//표시
	clock.innerHTML = "종료시간 : "+hourDisplay+":"+minute+":"+second;
	
          //만약 남은 시간이 0이하이면 종료
	if (diff <= 0) {
          	clearTimeout(id);
          	clock.innerHTML ="마감";
          }
}
//window.onload = displayTime; // 문서가 로딩될 때 수행할 함수 설정
</script>

        <div class="tableRow">
		</div>
			<div class="main">
					<c:forEach items='${requestScope["auctionList"]}' var="auction" varStatus="loopStatus">
					<div style="float: left;">
					<form action="">
					<table>
	                    <tr>
	                        <td class="auctionimage">
	                            <a href="/Action/AuctionBoard?action=read&pageNumber=${currentPageNumber}&boardNum=${auction.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&categoryType=${param.categoryType}">
	                                <img src="img/tmp/th_${auction.mainImage}">
	                            </a>
	                        </td>
	                   </tr>
	                   <tr>
	                        <td class="auctiontitle">
	                            <a href="/Action/AuctionBoard?action=read&pageNumber=${currentPageNumber}&boardNum=${auction.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&categoryType=${param.categoryType}">${auction.title}</a>
	                        </td>
	                   </tr>
	                   <tr>
	                        <td class="auctioncurrentPrice">${auction.currentPrice}</td>
	                   <tr>
	                        <td class="auctionendTime"><textarea hidden="true" rows="" cols="" name="endTime" class="endTime">${auction.endTime}</textarea>
	                        	${auction.endTime}까지
	                        </td>
	                    </tr>
	                    <tr>
		                    <td><span class="clock" > <label onload="displayTime2(this);">표시</label></span>
		                    </td>
	                    </tr>
                    </table>
                    </form>
                    </div>
                    </c:forEach>
            	<table id="allauctionlist">
				<tfoot>
					<tr>
						<td id="pagenavigator" colspan="5">
							<c:if test="${startPageNumber > 1}">
								<a href="/Action/AuctionBoard?action=main&pageNumber=${startPageNumber - 1}&searchType=${param.searchType}&searchText=${param.searchText}">이전</a>
							</c:if>
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
							<c:if test="${endPageNumber < totalPageCount}">
								<a href="list?pageNumber=${endPageNumber + 1}&searchType=${param.searchType}&searchText=${param.searchText}">다음</a>
							</c:if>
						</td>
					</tr>
				</tfoot>
            	</table>
        	</div>
