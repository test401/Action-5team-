// 해당 url로 이동하는 기능
function goUrl(url){
	location.href=url;
}

// 삭제 여부를 확인 후 해당 url로 이동하는 기능
function deleteCheck(url){
	if(confirm("정말로 삭제하시겠습니까?")){
		goUrl(url);
	}
}

function writeCheck(url){
	if(confirm("회원만 글작성만 됩니다. 회원가입을 하시겠습니까?")){
		goUrl(url);
	}
}

//검색 폼의 공백을 체크하는 기능
function searchCheck(form){
	if(form.searchText.value.length==0){
		alert("검색어를 입력하세요.");
		searchInput.focus();
		return;
	}
	form.submit();
}

//작성/수정 폼의 공백을 체크하는 기능
function boardWriteCheck(form) {
	if (form.title.value.length == 0) {
		alert("제목을 입력하세요.");
		form.title.focus();
		return;
	}

	if (form.startPrice.value.length == 0) {
		alert("시작가를 입력하세요.");
		form.startPrice.focus();
		return;
	}
	if (form.endTime.value.length == 0) {
		alert("기간을 선택하세요.");
		form.endTime.focus();
		return;
	}

	if (CKEDITOR.instances.contents.getData().length == 0) {
		alert("내용을 입력하세요.");
		CKEDITOR.instances.contents.focus();
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