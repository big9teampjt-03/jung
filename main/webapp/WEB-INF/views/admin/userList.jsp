<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>

<div class="container mt-3">
	<h2>회원 목록(${count})</h2>
	<table class="table table-hover" style="text-align: center;">
		<thead>
			<tr>
				<th>고객번호</th>
				<th>아이디</th>
				<th>전화번호</th>
				<th>애완견 이름</th>
				<th>삭제</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${members.content }" var="mb" varStatus="st">
				<tr>
					<td>${mb.memberid }</td>
					<td>${mb.username }</td>
					<td>${mb.phone }</td>
					<td>${mb.petname }</td>
					<td><a href='javascript:fdel(${mb.memberid })'>삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<div class="d-flex justify-content-between mt-5 mr-auto">
	<ul class="pagination">
		<c:if test="${members.first==false }">
			<li class="page-item"><a class="page-link"
				href="?page=${members.number-1 }">이전</a></li>
		</c:if>
		<c:if test="${members.last==false }">
			<li class="page-item"><a class="page-link"
				href="?page=${members.number+1 }">다음</a></li>
		</c:if>
	</ul>
</div>
</div>
<script>
	function fdel(id) {
		if (confirm("정말 삭제할까요?") == false)
			return false;
		$.ajax({
			type : "delete",
			url : "/delete/" + id
		}).done(function(resp) {
			alert("삭제 성공")
			location.href = "list"
		}).fail(function() {
			alert("삭제 실패")
		})
	}
</script>
<%@ include file="/WEB-INF/views/includes/footer.jsp"%>
