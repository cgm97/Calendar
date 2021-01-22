<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/css/FullCalendar.css" />" rel='stylesheet' />
<script src='<c:url value="/js/FullCalendar.js" />'></script>
<script src='<c:url value="/js/custom.js" />'></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: ''
      },
      locale : "ko",
      defaultView: 'dayGridMonth',
      businessHours: true, // 휴일 표시
      selectable: true,    
      
      dateClick : function(info){ // 선택한 날짜 값 뽑아내기
    	  const clickDate = info.dateStr
    	  const strArr = clickDate.split('-');
    	  const year = strArr[0]
    	  const month = strArr[1]
    	  const day = strArr[2]
    	  /* alert(year+"년"+month+"월"+day+"일"); */
    	  add_ClickSchedule(year,month,day)
      },
    });

    calendar.render();
    calendar.addEvent({'title':'test', 'start':'2021-01-22', 'end':'2021-01-25'});
  });
</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }
  .add-btn {
  	position: absolute;
  	top: 1px;
  	right: 0px;
  	background: #111000;
  	border: 0;
  	color: white;
  	height: 35px;
  	border-radius: 3px;
  	width: 100px;
  }

</style>
	<title>일정 관리 프로젝트</title>
</head>
	<body>
		<h2 style="text-align: center;">My Calendar</h2>
	  <div id='calendar' style="position: relative;" >
	  	<button class="add-btn" type="button" onclick="add_btnSchedule();">일정추가</button>
	  </div>
	</body>
</html>