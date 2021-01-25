<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/css/FullCalendar.css" />" rel='stylesheet' />
<script src='<c:url value="/js/FullCalendar.js" />'></script>
<script src='<c:url value="/js/Custom_Calendar.js" />'></script>
<script src='<c:url value="/js/gcal.js" />'></script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,today,next',
        center: 'title',
        right: ''
      },
      locale : "ko",
      defaultView: 'dayGridMonth',
      businessHours: true, // 휴일 표시
      selectable: true,    
      dateClick : function(info){ // 선택한 날짜 값 뽑아내기
    	  const clickDate = info.dateStr
/*     	  const strArr = clickDate.split('-');
    	  const year = strArr[0]
    	  const month = strArr[1]
    	  const day = strArr[2] */
    	  /* alert(year+"년"+month+"월"+day+"일"); */
    	  add_ClickSchedule(clickDate)
      }
      ,googleCalendarApiKey : "AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE" 
      ,eventSources : [
      // 대한민국의 공휴일
          {
                googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com"
              , className : "koHolidays"
              , color : "#FF0000"
              , textColor : "#FFFFFF"
          }]
      // 달력 일정 목록 
      ,events: [
    	  <c:forEach var="showList" items="${showList}">
    	  {
    		  title : '${showList.getTitle()}',
    		  start : '${showList.getStartDate()}'
    	  },
    	  </c:forEach>
    	  {
    		  title : 'event2',
    		  start : '2021-01-23'
    	  }
      ]
    });

    calendar.render();
  });
</script>

	<title>일정 관리 프로젝트</title>
</head>
	<body>
		<h2 style="text-align: center;">My Calendar</h2>
	  <div id='calendar' style="position: relative;" >
	  	<button class="add-btn" type="button" onclick="add_btnSchedule();">일정추가</button>
	  </div>
	</body>
</html>