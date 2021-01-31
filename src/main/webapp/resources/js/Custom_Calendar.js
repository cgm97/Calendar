  
function add_btnSchedule(){ // 버튼 클릭 일정 추가
	var url = "btnSelected";
	var name = "일정 추가";
	var option = "width = 600, height = 600 left = 100, top=50,location=no";
	window.open(url,name,option)

};

function add_ClickSchedule(clickDate){ // 클릭된 날짜부터 일정 추가
	var url = "calendarSelected?date="+clickDate;
	var name = "일정 추가";
	var option = "width = 600, height = 600 left = 100, top=50,location=no";
	window.open(url,name,option)

	//alert(year+"년"+month+"월"+day+"일");
};

function click_Calendar(calendarNo){ // 클릭된 일정 정보 불러오기 - 수정
	var url = "calendarUpdate?no="+calendarNo;
	var name = "일정 수정";
	var option = "width = 600, height = 600 left = 100, top=50,location=no";
	window.open(url,name,option)
};

$(function(){ // datepicker 선언
    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd'         //Input Display Format 변경
        ,showOtherMonths: true         //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true     //년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true             //콤보박스에서 년 선택 가능
        ,changeMonth: true             //콤보박스에서 월 선택 가능         
        ,yearSuffix: "년"             //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12']                     //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토']                                         //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']                 //달력의 요일 부분 Tooltip 텍스트
    });                   
    $("#startDate").datepicker();
    $("#endDate").datepicker();
});

function send_save(){ // ajax 비동기 처리 - 일정 추가
	var data = JSON.stringify($('#calendarData').serializeObject()); //form 자체의 내용을 전달
	
	/*var data = {}; // input에 입력된 내용을 전달 - 하지만 내가 원하는건 클릭한 날짜를 전달해야하지만, null 이 전달됨
	data["title"] = $("#title").val();
	data["startDate"] = $("#starDate").val(); // Null 이 입력됨..
	data["endDate"] = $("#endDate").val();
	data["content"] = $("#content").val();*/
	
	$.ajax({
		data : data,	//JSON.stringify(data)
		url : "/calendar/addCalendar",
		type : "POST",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		success:function(data){
			opener.parent.location.reload();
			alert("일정 추가 성공");
			window.close();
		},
	});
};

function update(){ // ajax 비동기 처리 - 일정 수정
	var data = JSON.stringify($('#calendarUpDate').serializeObject()); //form 자체의 내용을 전달

	$.ajax({
		data : data,	//JSON.stringify(data)
		url : "/calendar/editCalendar",
		type : "POST",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		success:function(data){
			opener.parent.location.reload();
			alert("일정 수정 성공");
			window.close();
		},
	});
};

function btnDelete(){ // ajax 비동기 처리 - 일정 삭제
	var data = $("#calendarNo").val(); // 일정번호
	console.log(data);
	$.ajax({
		data : data,
		url : "/calendar/deleteCalendar",
		type : "POST",
		contentType : "application/json; charset=UTF-8",
		success:function(data){
			opener.parent.location.reload();
			alert("일정 삭제 성공");
			window.close();
		},
	});
};