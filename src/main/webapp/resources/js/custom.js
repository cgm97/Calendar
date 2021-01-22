function add_btnSchedule(){ // 버튼 클릭 일정 추가
	var url = "btnSelected";
	var name = "일정 추가";
	var option = "width = 600, height = 600 left = 100, top=50,location=no";
	window.open(url,name,option)

};

function add_ClickSchedule(year,month,day){ // 클릭된 날짜부터 일정 추가
	var url = "calendarSelected?year="+year+"&month="+month+"&day="+day
	var name = "일정 추가";
	var option = "width = 600, height = 600 left = 100, top=50,location=no";
	window.open(url,name,option)

	//alert(year+"년"+month+"월"+day+"일");
};