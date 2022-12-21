<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>

<div class="container">
<h3>${user.username}회원정보</h3>
<form action="/update/${user.id }">
	<div class="form-group">
		<label for="id">아이디:</label>
		 <input type="text" class="form-control" id="id" name="id" value="${user.userid }" readonly="readonly">
	</div>
		<div class="form-group">
		<label for="username">이름:</label>
		 <input type="text" class="form-control" id="username" name=username value="${user.username }" readonly="readonly">
	</div>
		<div class="form-group">
		<label for="addr">주소:</label>
		 <input type="text" class="form-control" id="addr" name="addr" value="${user.addr }" readonly="readonly">
	</div>
		<div class="form-group">
		<label for="email">이메일:</label>
		 <input type="email" class="form-control" id="email" name="email" value="${user.email }" readonly="readonly">
	</div>
<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="memo" name="memo"
			readonly="readonly">${user.memo }</textarea>
	</div>
<button class="btn btn-secondary btn-sm">수정</button>
<button type="button" onclick="funDelete(${user.userid})">삭제</button>
</form>
</div>
<<script>
function funDelete(userid){
	$.ajax({
		type:"delete",
		url: "/delete/"+userid,
	})
	.done(function(resp){
		alert(resp+"번 삭제 성공")
		location.href = "/list"
	})
	.fail(function(){
		alert("삭제 실패")
	})
}
</script>
