// 현재 시각을 표시하는 함수 선언
function displayTime() {
	var elt = document.getElementById("clock"); // id="clock"인 요소 찾기
	var now = new Date(); // 현재 시각 얻기
	var date = new Date();// 마감날짜
	date.setHours(24, 0, 0, 0);// 마감시간
	var endTime = parseInt(date.getTime() / 1000);// 마감시간 초로만들기
	var nowTime = parseInt(now.getTime() / 1000);// 현재시간 초로만들기
	var diff = parseInt(endTime - nowTime);// 시간차 계산

	// 시간을 구하기 위한 일수 계산
	dayTemp = Math.floor((diff / 86400));
	// 시간을 구함
	hourDisplay = Math.floor((diff) / 3600);
	hour = Math.floor((diff - dayTemp * 86400) / 3600);
	// 분을 구함
	minute = Math.floor(((diff - dayTemp * 86400) - (hour * 3600)) / 60);
	// 초를 구함
	second = diff % 60;

	var id = setTimeout(displayTime, 1000); // 1초 후에 재 실행
	// 표시
	elt.innerHTML = "종료시간 : " + hourDisplay + ":" + minute + ":" + second;
	// 만약 남은 시간이 0이하이면 종료
	if (diff <= 0) {
		clearTimeout(id);
		elt.innerHTML = "마감";
	}

}
window.onload = displayTime; // 문서가 로딩될 때 수행할 함수 설정
