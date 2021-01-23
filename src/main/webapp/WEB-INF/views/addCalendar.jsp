<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 추가</title>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
  
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src='<c:url value="/js/Custom.js" />'></script>
<script src='<c:url value="/js/jquery.serializeObject.js" />'></script>
</head>

<body>
	 <div>
	 	<h1>일정 추가</h1>
	 </div>
	 <div>
	 	<%-- <form action="<c:url value="/add"/>" method="POST"> --%>
	 	<form id="calendarData">
	 		<div>
	 			제목 : <input type="text" name="title" id="title" placeholder="제목 입력">
	 		</div>	
	 		<br>
	 		<div>
	 			시작 날짜 : <input type="text" name="startDate" id="startDate" size="12" value="${selectedCalendar.startDate}" />
					 <input type="button" value="달력" onclick="$('#startDate').datepicker('show');" />
	 		</div>	
	 		<br>
	 		<div>
	 			종료 날짜 : <input type="text" name="endDate" id="endDate" size="12" />
						<input type="button" value="달력" onclick="$('#endDate').datepicker('show');" />
	 		</div>	
	 		<br>
	 			설명 
	 		<div>
	 			<textarea name="content" id="content" rows="10" cols="30"></textarea>
	 		</div>
	 		<br>
	 	</form>
	 		<input type= "button" value="저장" onclick="send_save()"/>
	 		응답<textarea id="rtn">디버그정보</textarea>
	 </div>
</body>
</html>