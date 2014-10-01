// 해당 url로 이동하는 기능
function goUrl(url){
	location.href=url;
}

// 삭제 여부를 확인 후 해당 url로 이동하는 기능
function deleteCheck(url){
	if(confirm("정말로 탈퇴하시겠습니까?")){
		goUrl(url);
	}
}


function passwordCheck(inputField, errorSpan){
	var password = inputField.form.password.value;
	if(inputField.value !=password){
		errorSpan.innerHTML="비밀번호를 확인하세요.";
	}else{
		errorSpan.innerHTML="";
	}
}
//작성/수정 폼의 공백을 체크하는 기능
function boardWriteCheck(form) {
	if (form.id.value.length == 0) {
		alert("id을 입력하세요.");
		form.id.focus();
		document.getElementById("registerimg").setAttribute("src", "../images/registerimg.gif");
		return;
	}

	if (form.name.value.length == 0) {
		alert("이름을 입력하세요.");
		form.name.focus();
		document.getElementById("registerimg").setAttribute("src", "../images/registerimg.gif");
		return;
	}
	
	if (form.password.value.length == 0) {
		alert("비밀번호를 입력하세요.");
		form.password.focus();
		document.getElementById("registerimg").setAttribute("src", "../images/registerimg.gif");
		return;
	}
	
	if (form.checkPassword.value.length == 0) {
		alert("비밀번호 확인을 하세요.");
		form.writer.focus();
		document.getElementById("registerimg").setAttribute("src", "../images/registerimg.gif");
		return;
	}
	
	if (form.tel.value.length == 0) {
		alert("연락처를 입력하세요.");
		form.tel.focus();
		document.getElementById("registerimg").setAttribute("src", "../images/registerimg.gif");
		return;
	}
	
	if (form.address.value.length == 0) {
		alert("주소를 입력하세요.");
		form.address.focus();
		document.getElementById("registerimg").setAttribute("src", "../images/registerimg.gif");
		return;
	}

	form.submit();
}
// 폼 피륻가 비어있는지 여부를 체크하여 에러메시지를 출력
function checkNotEmpty(inputField, errorSpan){
	if(inputField.value.length == 0){
		errorSpan.innerHTML="입력하세요.";
	}else{
		errorSpan.innerHTML="";
	}
}

/*//작성/수정 폼의 공백을 체크하는 기능
function boardWriteCheck(){
	var form=document.writeForm;
	
	if(form.title.value.lenght==0){
		alert("제목을 입력하세요.");
		form.title.focus();
		return false;
	}
	
	if(form.writer.value.lenght==0){
		alert("이름을 입력하세요.");
		form.writer.focus();
		return false;
	}
	
	if(form.contents.value.lenght==0){
		alert("내용을 입력하세요.");
		form.contents.focus();
		return false;
	}
	return true;
}*/