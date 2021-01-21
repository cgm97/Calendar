<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/main.css" />" rel='stylesheet' />
<script src='<c:url value="/resources/js/main.js" />'></script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth'
      },
      locale : "ko",
      defaultView: 'dayGridMonth',
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      selectable: true,    
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
  	right: 70px;
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
	  <div id='calendar' style="position: relative;" />
	  <button class="add-btn" type="button" onclick="add_schedual();">일정추가</button>
	</body>
</html>