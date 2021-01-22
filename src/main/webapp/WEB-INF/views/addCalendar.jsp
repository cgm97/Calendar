<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 추가</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="./jquery-ui-1.12.1/datepicker-ko.js"></script>

<script type="text/javascript">
$(function(){
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
    $("#start").datepicker({
        onSelect:function(dateText, inst) {
            console.log(dateText);
        }
    });
    $("#end").datepicker({
        onSelect:function(dateText, inst) {
            console.log(dateText);
        }
    });
});
</script>
</head>
<body>
	 <div>
	 	<h1>일정 추가</h1>
	 </div>
	 <div>
	 	<form action="<c:url value="/add"/>" method="POST">
	 		<div>
	 			제목 : <input type="text" name="title" id="title" placeholder="제목 입력">
	 		</div>	
	 		<br>
	 		<div>
	 			시작 날짜 : <input type="text" name="start" id="start" size="12" value="${selectedCalendar.startDate}" />
					 <input type="button" value="달력" onclick="$('#start').datepicker('show');" />
	 		</div>	
	 		<br>
	 		<div>
	 			종료 날짜 : <input type="text" name="end" id="end" size="12" />
						<input type="button" value="달력" onclick="$('#end').datepicker('show');" />
	 		</div>	
	 		<br>
	 		<div>
	 			설명 : <input type="text" name="content" id="content" style="width: 150px; height: 150px;" />
	 		</div>	
	 		<br>
	 		<input type="submit" value="저장" />
	 	</form>
	 </div>
</body>
</html>