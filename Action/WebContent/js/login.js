$(function() {
	$('#loginButton').click(function() {
		jQuery.ajax({
			type: "POST",
			url: '/Action/MemberController',
			data:
				'action=login'+"&"+
				'memberID='+encodeURIComponent($("[name=loginmemberID]").val())+"&"+
				'password='+encodeURIComponent($("[name=loginpassword]").val()),
			dataType: 'text',
			success: function(text) {
				if(text==$("[name=loginmemberID]").val()){
					$('#sidebarMargin').empty();
					$('#sidebarMargin').html('<form id ="logoutck" action="/Action/MemberController?action=logout" method="POST"><table id="logouttable"> <tr> <td class="message">'+text+'님<br> 환영합니다.</td> <td><input type="submit" name="logout" value="로그아웃"></td></tr><tr><td><a href="/Action/MemberController?action=select">내정보</a></td><td><a href="">입찰리스트</a></td></tr></table></form>'
					);				
				}else{
					$('#loginerrormsg').html('<li>'+text+'</li>');
				}
			}
		});
	});
});


