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
    	  const clickDate = info.dateStr;
    	  add_ClickSchedule(clickDate);
      },
      eventClick: function(info){ // 클릭한 일정 값 뽑아내기
    	  const calendarNo = info.event.id;
    	  click_Calendar(calendarNo);
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
    	  // 나의 일정 목록
    	  <c:forEach var="showList" items="${getList}">
    	  {
    		  id 	: '${showList.getCalendarNo()}',
    		  title : '${showList.getTitle()}',
    		  start : '${showList.getStartDate()}',
    		  end 	: '${showList.getEndDate()}'
    	  },
    	  </c:forEach>
    	  {
    		  title : '',
    		  start : ''
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