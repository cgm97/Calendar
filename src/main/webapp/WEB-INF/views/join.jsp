<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body style="text-align: center">
	<form action="<c:url value="/join.do"/>" method="POST">
		<input type="text" id="loginId" name="loginId" placeholder="ID" /><br>
		<input type="password" id="loginPw" name="loginPw" placeholder="PASSWORD" /><br>
		<input type="text" id="name" name="name" placeholder="이름" /><br>
		<input type="text" id="email" name="email" placeholder="이메일" /><br>
		<input type="submit" value="회원가입" />
	</form>
</body>
</html>