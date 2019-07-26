<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
	<script type="text/javascript" src="//code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/additional-methods.js"></script>
	
	<title>회원가입</title>
	<style>
	*{
		margin: 0;
		padding : 0;
	}
	.main{
		margin-top:50px;
	}
	.row{
		margin: 5px 0px;
	}
	.fab.fa-amazon{
		font-size: 100px;
		color: red;
	}
	.error{
	color : red;
	padding : 0;
	}
	</style>
	<script type="text/javascript">
			
		//-1: 중복체크를해야하는경우 0: 회원가입이가능한경우 1:이미회원이라 회원가입불가능
		var isMember=-1;
		$(document).ready(function(){
			//아이디 중복검사를 통해 회원가입 가능한 아이디를 입력했더라도 이후에 아이디창을 통해 
			//아이디 값을 바꾼다면 다시 중복검사를 해야하기 때문에 
			//isMember변수값을 -1로 한다 
			$('#id').change(function(){
				isMember = -1;
			})
			
			$('#signup').submit(function(){
				if(isMember ==-1){
					alert('아이디 중복검사를 확인하세요 ')
					return false;
				}else if(isMember ==1){
					alert('이미 가입된 아이디입니다. 다른 아이디를 입력하세요  ')
					return false;
				}
				alert('회원가입에 성공했습니다.');
				return true;
			});
			$('#dup').click(function(){
			var id = $("#id").val();
			
			$.ajax({
		        async:true, 
		        type:'POST', //get,post중 선택
		        data:id, //서버에 요청시 보낼 매개변수
		        url:"<%=request.getContextPath()%>/dup", //요청할 URL
		        dataType:"json", //응답받을 데이터의 타입, json을 사용하기 위해 pom.xml에 의존성을 추가해야함
		        contentType:"application/json; charset=UTF-8",
		        success : function(data){
		        	console.log(data.isId);
		            if(!data.isId){
		            	alert('회원가입이 가능한 아이디입니다');
		            	isMember = 0;
		            }else{
		            	alert('이미 가입된 회원입니다.');
		            	isMember = 1;
		            }  	
		        },
		        error:function(request,status,error){
		            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		        }
			    });
				})
				$("#signup").validate({
			        rules: {
			            id: {
			                required : true,
			                minlength : 8,
			                maxlength : 13
			            },
			            pw: {
			                required : true,
			                minlength : 8,
			                maxlength : 13,
			                regex: /^\w*(\d[A-z]|[A-z]\d)\w*$/
			            },
			            pw2: {
			                required : true,
			                equalTo : pw
			            },
			            name: {
			                required : true,
			                minlength : 2
			            },
			            email: {
			                required : true,
			                email : true
			            }
			        },
			        //규칙체크 실패시 출력될 메시지
			        messages : {
			            id: {
			                required : "필수로입력하세요",
			                minlength : "최소 {0}글자이상이어야 합니다",
			                maxlength : "최대 {0}글자이하이어야 합니다"
			            },
			            pw: {
			                required : "필수로입력하세요",
			                minlength : "최소 {0}글자이상이어야 합니다",
			                maxlength : "최대 {0}글자이하이어야 합니다",
			                regex : "영문자, 숫자로 이루어져있으며 최소 하나이상 포함"
			            },
			            pw2: {
			                required : "필수로입력하세요",
			                equalTo : "비밀번호가 일치하지 않습니다."
			            },
			            name: {
			                required : "필수로입력하세요",
			                minlength : "최소 {0}글자이상이어야 합니다"
			            },
			            email: {
			                required : "필수로입력하세요",
			                email : "메일규칙에 어긋납니다"
			            }
			        }
			    });
			});
			$.validator.addMethod(
			    "regex",
			    function(value, element, regexp) {
			        var re = new RegExp(regexp);
			        return this.optional(element) || re.test(value);
			    },
			    "Please check your input."
				);	
		
	</script>
</head>
<body>
	<div>
		<div class="offset-4 col-4 border border-dark mt-5">
			<h1 class="text-center">회원가입</h1>
			<form method="post" action="<%=request.getContextPath()%>/signup" id="signup">
				<div class="row">
					<label class="col-4">아이디</label>
					<input type="text"class="form-control col-7" placeholder="아이디" name="id">
				</div>
				<div class="row">
					<label class="error offset-4 col-7" id="id-error" for="id"></label>
				</div>
				<div>
					<button type="button" class="btn btn-outline-success offset-4 col-7" id="dup">아이디 중복확인</button>
				</div>
				<div class="row">
					<label class="col-4">비밀번호</label>
					<input type="password"class="form-control col-7" placeholder="비밀번호" name="pw" id="pw">
				</div>
				<div class="row">
					<label class="error offset-4 col-7" id="pw-error" for="pw"></label>
				</div>
				<div class="row">
					<label class="col-4">비밀번호확인</label>
					<input type="password"class="form-control col-7" placeholder="비밀번호확인" name="pw2">
				</div>
				<div class="row">
					<label class="error offset-4 col-7" id="pw2-error" for="pw2"></label>
				</div>
				<div class="row">
					<label class="col-4">성별</label>
					<div class="col-8">
						<label class="form-check-label col-5">
							<input type="radio" class="form-check-input" name="gender" value="M" checked>남성
						</label>
						<label class="form-check-label">
							<input type="radio" class="form-check-input" name="gender" value="F">여성
						</label>
					</div>
				</div>
				<div class="row">
					<label class="col-4">이메일</label>
					<input type="email"class="form-control col-7" placeholder="이메일" name="email">
				</div>
				<div class="row">
					<label class="error offset-4 col-7" id="email-error" for="email"></label>
				</div>
				<div class="row">
					<label class="col-4">이름</label>
					<input type="text"class="form-control col-7" placeholder="이름" name="name">
				</div>
				<div class="row">
					<label class="error offset-4 col-7" id="name-error" for="name"></label>
				</div>
				<div class="offset-8 col-3 clearfix p-0">
					<button class="btn btn-primary float-right">가입</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>