<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src='<c:url value="/js/Custom_member.js" />'></script>
<script src='<c:url value="/js/AjaxSerializeObject.js" />'></script>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body style="text-align: center">
	<form id="loginData">
<!-- <form action="<c:url value="/login.do"/>" method="POST"> -->
		<input type="text" id="loginId" name="loginId" placeholder="ID" /><br>
		<input type="password" id="loginPw" name="loginPw" placeholder="PASSWORD" /><br><br>
	</form>
	<input type="button" value="로그인" onclick="login()" />
</body>
</html>