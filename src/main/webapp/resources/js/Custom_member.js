function login_form(){ // 버튼 클릭 로그인
	var url = "login";
	var name = "로그인";
	var option = "width = 600, height = 600 left = 100, top=50,location=no";
	window.open(url,name,option)

};

function login(){ // ajax 비동기 처리 - 로그인 처리
	var data = JSON.stringify($('#loginData').serializeObject()); //form 자체의 내용을 전달
	$.ajax({
		data : data,	//JSON.stringify(data)
		url : "/calendar/login.do",
		type : "POST",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		success:function(data){
			opener.parent.location.reload();
			window.close();
		},
	});
};