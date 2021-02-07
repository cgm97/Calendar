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
<script src='<c:url value="/js/Custom_member.js" />'></script>
<script src='<c:url value="/js/AjaxSerializeObject.js" />'></script>
<title>회원가입</title>
</head>
<body style="text-align: center">
	<form action="<c:url value="/join.do"/>" method="POST">
		<label>아이디    : </label><input type="text" id="loginId" name="loginId" placeholder="ID" /><button id="check" type="button" onclick="duplication();">중복확인</button><br>
		<div class="dupText"></div>
		<label>비밀번호 : </label><input type="password" id="loginPw" name="loginPw" placeholder="PASSWORD" /><br>
		<label>이름       : </label><input type="text" id="name" name="name" placeholder="이름" /><br>
		<label>이메일    : </label><input type="text" id="email" name="email" placeholder="이메일" /><br>
		<input type="submit" value="회원가입" />
		<input type="button" onclick="window.history.go(-1)" value="취소" />
	</form>
</body>
</html>