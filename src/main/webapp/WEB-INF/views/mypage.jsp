<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src='<c:url value="/js/Custom_member.js" />'></script>
<title>마이페이지</title>
</head>
<body style="text-align: center">
	<form  id='userInfo' action="<c:url value="/mypage.do"/>" method="POST">
		<label>아이디    : </label><input type="text" id="loginId" name="loginId" value="${loginedMember.loginId}" readOnly/><br>
		<label>비밀번호 : </label><input type="password" id="loginPw" name="loginPw" value="${loginedMember.loginPw}"/><br>
		<label>비밀번호 체크 : </label><input type="password" id="pwcheck" name="pwcheck" /><br>
		<div id="check-success">비밀번호가 일치합니다.</div>
		<div id="check-danger">비밀번호가 일치하지 않습니다.</div>
		<label>이름       : </label><input type="text" id="name" name="name" value="${loginedMember.name}" /><br>
		<label>이메일    : </label><input type="text" id="email" name="email" value="${loginedMember.email}" /><br><br>
		<label>가입날짜 : </label>${loginedMember.regDate}<br>
		<label>최근로그인 : </label>${loginedMember.lastLogin}<br>
		<input type="submit" id="submit" value="계정 수정" />
		<input type="submit" onclick='return confirm("해당 아이디  ${loginedMember.loginId}를"+" 정말 삭제 하시겠습니까?")'
				 formaction="<c:url value="/delete" />" value="계정 삭제" /><br>
		<input type="button" onclick="location.href='<c:url value="/"/>'" value="Home" />
	</form>
</body>
</html>