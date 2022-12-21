<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>

<div class="container mt-3">
	<h3>일반 회원 리스트</h3>
	<table class="table table-hover" style="text-align: center;">
		<thead>
			<tr>
				<th>아이디</th>
				<th>닉네임</th>
				<th>휴대폰번호</th>
				<th>가입일</th>
				<th>권한</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${lists}" var="user">
				<tr>
					<td><a href="detail/${user.userid}"> ${user.username }</a></td>
					<td>${user.nickname }</td>
					<td>${user.tel }</td>
					<td>${user.regdate }</td>
					<td>${user.role }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script>
	
</script>
<%@include file="../includes/footer.jsp"%>
