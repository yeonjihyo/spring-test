<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- 공통으로 들어가는 jsp를 따로 관리할때 불러오는 코드 -->
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function(){
		$('#perPageNum').change(function(){
			location.href 
				= '<%=request.getContextPath()%>/board/list?perPageNum=' + $(this).val();
		})
	})
	//스크립트에서 위치를 바꿔줄때
	//location.href='가고싶은 uri'
</script>
</head>

<body>
<jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>
	<h1>게시판</h1>
	<div class="form-group col-3 float-right">
	  <select class="form-control" id="perPageNum">
	    <option value="5" <c:if test="${pageMaker.criteria.perPageNum ==5}">selected</c:if>>5개씩보기</option>
	    <option value="10" <c:if test="${pageMaker.criteria.perPageNum ==10}">selected</c:if>>10개씩보기</option>
	    <option value="15" <c:if test="${pageMaker.criteria.perPageNum ==15}">selected</c:if>>15개씩보기</option>
	    <option value="20" <c:if test="${pageMaker.criteria.perPageNum ==20}">selected</c:if>>20개씩보기</option>
	  </select>
	</div>
	<table class="table">
		<tr>
			<th width="5%">번호</th>
			<th width=55%">제목</th>
			<th width="10%">작성자</th>
			<th width="20%">등록일</th>
			<th width="10%">조회수</th>
		</tr>
		<!-- 맨위에 줄에 있는 url을 복사한 이유는 아래처럼 사용하기 위해-->
		<!-- 향상된 for문이라고 생각  -->
		<c:forEach var="tmp" items="${board}"> <!-- 컨트롤러가보내줄보드  -->
		<!-- items에서 리스트를 하나씩 끄집어 내서 board에 저장--> 
			<tr>
				<th>${tmp.num}</th> <!-- .뒤는 getter를 호출-->
				<th><a href="<%=request.getContextPath()%>/board/display?num=${tmp.num}">${tmp.title}</a></th>
				<!-- ?는 데이터를 전송하는 역할 -->
				<th>${tmp.writer}</th>
				<th>${tmp.registered}</th>
				<th>${tmp.views}</th>
			</tr>
		</c:forEach>	
	</table>
	<a href="<%=request.getContextPath()%>/board/register">
		<button type="button" class="btn btn-outline-danger">등록</button>
	</a>
	<ul class="pagination" style="justify-content: center;">
	    <c:if test="${pageMaker.prev}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.startPage-1}&perPageNum=${pageMaker.criteria.perPageNum}&search=${pageMaker.criteria.search}&type=${pageMaker.criteria.type}">Previous</a>
	        </li>
	    </c:if>
	    <!-- 현재 페이지 선택되게 하는  -->
	    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage}" var="index">
	    	<c:if test="${pageMaker.criteria.page == index}">
		        <li class="page-item active">
		            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}&perPageNum=${pageMaker.criteria.perPageNum}&search=${pageMaker.criteria.search}&type=${pageMaker.criteria.type}">${index}</a>
		        </li>
	        </c:if>
	        <c:if test="${pageMaker.criteria.page != index}">
		        <li class="page-item">
		            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}&perPageNum=${pageMaker.criteria.perPageNum}&search=${pageMaker.criteria.search}&type=${pageMaker.criteria.type}">${index}</a>
		        </li>
	        </c:if>
	    </c:forEach>
	    <c:if test="${pageMaker.next}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.endPage+1}&perPageNum=${pageMaker.criteria.perPageNum}&search=${pageMaker.criteria.search}&type=${pageMaker.criteria.type}">Next</a>
	        </li>
	    </c:if>
	</ul>
	<form action="<%=request.getContextPath()%>/board/list" method="get">
		<div class="form-group  m-auto clearfix col-9">
		  <select class="form-control col-4 float-left" name="type">
		    <option value="0">선택</option><!-- 내가보내야하는값이다른경우에 value씀  -->
		    <option value="1" <c:if test="${pageMaker.criteria.type ==1}">selected</c:if>>제목</option>
		    <option value="2" <c:if test="${pageMaker.criteria.type ==2}">selected</c:if>>내용</option>
		    <option value="3" <c:if test="${pageMaker.criteria.type ==3}">selected</c:if>>작성자</option>
		  </select>
		  <input type="text" class=" float-left col-4" value="${pageMaker.criteria.search}" name="search">
		  <button type="submit" class="btn btn-outline-danger float-left col-4">검색</button>
		</div>
	</form>
</body>
</html>