<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<script type="text/javascript">
</script>
<body><!-- 괄호안은 속성명임 -->
	<jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>
	<div class="container-fluid" style="margin-top: 80px;">
		<div class="form-group">
		  <label>제목</label>
		  <input type="text" class="form-control" name="title" value="${board.title}" readonly>
		</div>
		
		<div class="form-group">
		  <label>작성자</label>
		  <input type="text" class="form-control" name="writer" value="${board.writer}" readonly>
		</div>
		<div class="form-group">
		  <label>작성일</label>
		  <input type="text" class="form-control" name="registered" value="${board.registered}" readonly>
		</div>
		<div class="form-group">
		  <label>조회수</label>
		  <input type="text" class="form-control" name="views" value="${board.views}" readonly>
		</div>
		<div class="form-group">
		  <label>내용</label>
		  <textarea rows="10" class="form-control" name="contents" readonly>${board.contents}</textarea>
		</div>
		<div class="form-group">
		  <label>첨부파일</label>
		  <input type="text" class="form-control" name="file" value="${board.file}" readonly>
		</div>
		<a href="<%=request.getContextPath()%>/board/list">
			<button type="button" class="btn btn-outline-danger">목록</button>
		</a>
		<a href="<%=request.getContextPath()%>/board/register">
			<button type="button" class="btn btn-outline-danger">등록</button>
		</a>
		<c:if test="${user ne null && user.id eq board.writer}">
		<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}"><!-- 수정하려면 게시판 번호가 필요  -->
			<button type="button" class="btn btn-outline-danger">수정</button>
		</a>
		<a href="<%=request.getContextPath()%>/board/delete?num=${board.num}"><!-- 수정하려면 게시판 번호가 필요  -->
			<button type="button" class="btn btn-outline-danger">삭제</button>
		</a>
		</c:if>
	</div>
	
</body>
</html>