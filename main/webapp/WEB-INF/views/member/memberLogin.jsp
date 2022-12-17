<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<div class="container">
	<form action="login" method="post" id="frm">
		<div class="form-group">
			<label for="username">이메일:</label> <input type="email" id="username"
				name="username" class="form-control" placeholder="이메일을 입력하세요." />
		</div>
		<div class="form-group">
			<label for="passowrd">비밀번호:</label> <input type="password"
				class="form-control" id="password" placeholder="비밀번호를 입력하세요."
				name="password">
		</div>
		<button type="button"class="btn btn-primary" id="btnLogin">로그인</button>
	</form>
	<script>
	$("#btnLogin").click(function(){
		if($("#username").val()==""){
			alert("이메일을 입력하세요.")
			$("#username").focus();
			return false;
		}
		if($("#password").val()==""){
			alert("비밀번호를 입력하세요.")
			$("#password").focus();
			return false;
		}
		$("#frm").submit();
		
	})
	</script>
</div>
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>