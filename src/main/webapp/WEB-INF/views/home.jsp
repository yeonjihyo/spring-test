    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	로그인
	<!-- ${user} -->
</h1>
<form method="post">
	아이디 <input type="text" name="id"><br>
	비밀번호 <input type="password" name="pw"><br>
	<button>로그인</button>
	<a href="<%=request.getContextPath()%>/password/find">비밀번호 찾기</a>
</form>

</body>
</html>