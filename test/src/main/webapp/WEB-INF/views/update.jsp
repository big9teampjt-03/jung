<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>

<div class="container mt-3">
<h3>${principal.user.nickname}님의 회원 정보</h3>
<form action="/update/${user.userid }">
<button type="button" class="btn btn-info btn" onclick="location.href='/docupdate/${user.userid }';" id="btnDocUpdate">전문가 정보 입력하기</button>
	<div class="form-group">
		 <input type="hidden" class="form-control" id="userid" name="userid" value="${user.userid }" readonly="readonly">
	</div>
		<div class="form-group">
		<label for="username">이메일:</label>
		 <input type="email" class="form-control" id="username" name="username" value="${principal.user.username }" readonly="readonly">
	</div>
			<div class="form-group">
		<label for="password">비밀번호:</label>
		 <input type="password" class="form-control" id="password" name="password">
	</div>
	<div class="form-group">
			<label for="pass_check">비밀번호 확인:</label> <input type="password"
				class="form-control" id="pass_check" placeholder="패스워드를 한번 더 입력하세요."
				name="pass_check">
		</div>
		<div class="form-group">
		<label for="nickname">닉네임:</label>
		 <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname }" >
	</div>
		<div class="form-group">
		<label for="tel">휴대폰번호:</label>
		 <input type="tel" class="form-control" id="tel" name="tel" value="${user.tel }">
	</div>

<button type="button" class="btn btn-secondary btn" id="btnUpdate">수정하기</button>
</form>
</div>

<script>
$("#btnUpdate").click(function(){
	var dataParam = {
		"userid": $("#userid").val(),
		"username": $("#username").val(),
		"password": $("#password").val(),
		"nickname": $("#nickname").val(),
		"tel": $("#tel").val(),
	}
	$.ajax({
		type:'put',
		url: '/update/',
		data:JSON.stringify(dataParam),
		contentType: 'application/json;charset=utf-8'
	})
	.done(function(){
		alert("회원 정보가 수정되었습니다. 다시 로그인 해 주세요.")
		location.href="/login"
	})
	.fail(function(){
		alert("회원 정보 수정에 실패했습니다.")
	})
})
</script>
<%@ include file="/WEB-INF/views/includes/footer.jsp"%>