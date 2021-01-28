<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<script src='<c:url value="/js/Custom_Calendar.js" />'></script>
	<script src='<c:url value="/js/AjaxSerializeObject.js" />'></script>
		<title>일정 추가</title>
</head>
<body>
	 <div>
	 	<h1>일정 수정</h1>
	 </div>
	 <div>
	 	<form id="calendarUpDate">
	 		<div>
	 			제목 : <input type="text" name="title" id="title" placeholder="제목 입력" value="${getListByNo.title}" />
	 		</div>	
	 		<br>
	 		<div>
	 			시작 날짜 : <input type="text" name="startDate" id="startDate" size="12" value="${getListByNo.startDate}" />
					 <input type="button" value="달력" onclick="$('#startDate').datepicker('show');" />
	 		</div>	
	 		<br>
	 		<div>
	 			종료 날짜 : <input type="text" name="endDate" id="endDate" size="12" value="${getListByNo.endDate}" />
						<input type="button" value="달력" onclick="$('#endDate').datepicker('show');" />
	 		</div>	
	 		<br>
	 			설명 
	 		<div>
	 			<textarea name="content" id="content" rows="10" cols="30">${getListByNo.content}</textarea>
	 		</div>
	 		<br>
	 	</form>
	 		<input type= "button" value="수정" onclick=""/>
	 </div>
</body>
</html>