<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>


<div class="container mt-3">
	<h3>회원 가입하기</h3>
	<div class="form-group">
		<label for="username">이메일:</label> <input type="email"
			class="form-control" id="username" placeholder="이메일을 입력하세요."
			name="username">
	</div>
	<input type="button" class="btn btn-secondary" onclick="btnEmail"
		value="중복확인" />
		
	<div class="form-group">
		<label for="password">비밀번호:</label> <input type="password"
			class="form-control" id="password" placeholder="패스워드를 입력하세요."
			name="password">
	</div>
	
	<div class="form-group">
		<label for="pass_check">비밀번호 확인:</label> <input type="password"
			class="form-control" id="pass_check" placeholder="패스워드를 한번 더 입력하세요."
			name="pass_check">
	</div>

	<div class="form-group">
		<label for="nickname">닉네임:</label> <input type="text"
			class="form-control" id="nickname" placeholder="닉네임을 입력하세요."
			name="nickname">
	</div>
	<div class="form-group">
		<label for="tel">휴대폰번호:</label> <input type="text"
			class="form-control" id="tel" placeholder="휴대폰번호를 입력하세요." name="tel">
	</div>
	<button class="btn btn-secondary" id="btnJoin">회원가입</button>

	<script>
		$("#btnJoin").click(function() {
			if ($("#username").val() == "") {
				alert("이메일을 입력하세요.")
				$("#username").focus();
				return false;
			}
			if ($("#password").val() == "") {
				alert("패스워드를 입력하세요.")
				$("#password").focus();
				return false;
			}
			if($("#password").val() != $("#pass_check").val()){
				alert("패스워드가 일치하지 않습니다.")
				$("#pass_check").focus();
				return false;
			}
			if ($("#nickname").val() == "") {
				alert("닉네임을 입력하세요.")
				$("#nickname").focus();
				return false;
			}
			if ($("#tel").val() == "") {
				alert("휴대폰번호를 입력하세요.")
				$("#tel").focus();
				return false;
			}
			var dataParam = {
				"username" : $("#username").val(),
				"password" : $("#password").val(),
				"pass_check" : $("#pass_check").val(),
				"nickname" : $("#nickname").val(),
				"tel" : $("#tel").val()
			}
			$.ajax({
				type : "POST",
				url : "/join",
				contentType : "application/json;charset=utf-8",
				data : JSON.stringify(dataParam)
			}).done(function(resp) {
				if (resp == "success") {
					alert("회원 가입이 정상적으로 처리되었습니다.")
					location.href="/login"
				} else if (resp == "fail") {
					alert("중복된 이메일입니다.")
					$("#username").val("")
				}
			}) //done
			.fail(function() {
				alert("회원 가입에 실패했습니다.")
			})
		})
		
	</script>
</div>
<%@ include file="/WEB-INF/views/includes/footer.jsp"%>