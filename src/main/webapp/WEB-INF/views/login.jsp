<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body style="text-align: center">
	<form action="<c:url value="/login.do"/>" method="POST">
		<input type="text" id="loginId" name="loginId" placeholder="ID" /><br>
		<input type="password" id="loginPw" name="loginPw" placeholder="PASSWORD" /><br><br>
		<input type="submit" value="로그인" />
		<button type="button" onclick="location.href='join'">회원가입</button>
	</form>
</body>
</html>