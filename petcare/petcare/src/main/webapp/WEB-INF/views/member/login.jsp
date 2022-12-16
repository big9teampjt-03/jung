<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container">
	<form action="/loginPro" method="post">
		<div class="form-group">
			<label for="username">아이디:</label> <input type="text" id="username"
				name="username" class="form-control" placeholder="Enter ID" />
		</div>
		<div class="form-group">
			<label for="pass">비밀번호:</label> <input type="password"
				class="form-control" id="pass" placeholder="Enter pass"
				name="password">
		</div>
		<button class="btn btn-primary">로그인</button>
	</form>
</div>