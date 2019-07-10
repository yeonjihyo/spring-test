<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${oneBoard}<br>

	${oneBoard.contents}<br>
	${oneBoard.writer}<br>
	${oneBoard.registered}<br>
	${oneBoard.title}<br>
	${oneBoard.views}<br>
</body>
</html>