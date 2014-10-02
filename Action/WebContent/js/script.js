function removeCheck(url) {
	if (confirm("정말로 탈퇴를 하시겠습니까?")) {
		location.href = url;
	}
}
function deleteCheck(form) {
     var chk = document.getElementsByName("productID"); 
     var len = chk.length; 
     var ck = false; 
     for(var i=0; i<len; i++){ 
         if(chk[i].checked == true){ 
             ck=true;
         } 
     } 
    
	if(ck){
		if (confirm("정말로 삭제를 하시겠습니까?")) {
			form.submit();
		}
	}

}