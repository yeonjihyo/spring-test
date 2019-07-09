<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	메인페이지 
</h1>

<P>  The time on the server is ${serverTime}. </P>
<form method="post">
	<input type="text" name="id">
	<input type="text" name="pw">
	<button>로그인</button>
</form>
${email}
</body>
</html>
