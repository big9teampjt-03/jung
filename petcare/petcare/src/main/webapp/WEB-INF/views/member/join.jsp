<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<div class="container">
	<h3>JOIN</h3>
	<div class="form-group">
		<label for="email">이메일:</label> <input type="text"
			class="form-control" id="email" placeholder="Enter Email"
			name="email">
	</div>
	<div class="form-group">
		<label for="password">비밀번호:</label> <input type="password"
			class="form-control" id="password" placeholder="Enter Password"
			name="password">
	</div>
	<div class="form-group">
			<label for="pass_check">비밀번호 확인:</label> <input type="password"
				class="form-control" id="pass_check" placeholder="Enter pass_check"
				name="pass_check">
		</div>

	<div class="form-group">
		<label for="nick">닉네임:</label> <input type="text"
			class="form-control" id="nick" placeholder="Enter Nickname"
			name="nick">
	</div>
	<div class="form-group">
		<label for="tel">휴대폰번호:</label> <input type="text"
			class="form-control" id="tel" placeholder="Enter Tel"
			name="tel">
	</div>
	<button class="btn btn-secondary" id="btnJoin">회원가입</button>

	<script>
		$("#btnJoin").click(function() {
			if ($("#email").val() == "") {
				alert("이메일 입력하세요")
				$("#email").focus();
				return false;
			}
			if ($("#password").val() == "") {
				alert("비밀번호 입력하세요")
				$("#password").focus();
				return false;
			}
			if ($("#pass_check").val() == "") {
				alert("비밀번호 입력하세요")
				$("#pass_check").focus();
				return false;
			}
			if ($("#nick").val() == "") {
				alert("닉네임을 입력하세요")
				$("#nick").focus();
				return false;
			}
			if ($("#tel").val() == "") {
				alert("번호를 입력하세요")
				$("#tel").focus();
				return false;
			}
			var dataParam = {
				"email" : $("#email").val(),
				"password" : $("#password").val(),
				"pass_check" : $("#pass_check").val(),
				"nick" : $("#nick").val(),
				"tel" : $("#tel").val()
			}
			$.ajax({
				type : "POST",
				url : "/join",
				contentType : "application/json;charset=utf-8",
				data : JSON.stringify(dataParam)
			}).done(function(resp) {
				if (resp == "success") {
					alert("회원가입성공")
					location.href="/login"
				} else if (resp == "fail") {
					alert("이메일 중복")
					$("#email").val("")
				}
			}) //done
			.fail(function() {
				alert("회원가입실패")
			})
		})
	</script>
</div>