<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="../includes/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
<input type="hidden" name="questionID" id="questionID" value="${board.questionID }" />
<table class="table table-hover">
		<tr>
			<th>글번호</th>
			<td>${board.questionID }</td>
			<th>조회수</th>
			<td>${board.hitcount }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.username }</td>
			<th>작성일</th>
			<td><fmt:formatDate value="${board.regdate }" pattern='yyyy-MM-dd'/></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${board.title }</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3">${board.content }</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td colspan="3"> 
			<img class="card-img-top" src="/resources/img/${board.questionimage }" style="width:100%">
		</tr>
	<br/><br/>
	</table>
<%-- 	<c:if test="${principal.user.username==board.user.username}"> --%>
	<button type="button" class="btn btn-primary"  id="btnUpdate">수정</button>
		<button type="button" class="btn btn-secondary" id="btnDelete">삭제</button>
		<%-- </c:if> --%>
	</div>
	
	<br/><br/>
	
	<div class="container mt-5">
		<div class="form-group">
			<label for="comment">Comment:</label>
			<textarea class="form-control" rows="5" id="msg" name="text"></textarea>
		</div>
		
		<button type="button" class="btn btn-success" id="BtnComment">댓글쓰기</button>
	
	<div class="mt-5">댓글(<span class ="replyCnt"></span>)</div>
	
	<div id="replyResult"></div>
	</div>
	
	<script>
	var init=function(){
		$.ajax({
			type:"get",
			url:"/reply/qlist/"+$("#comquestionID").val()
		})
		.done(function(resp){
			var str="<table class='table table-hover'>"
			$.each(resp,function(key,val){
				str+="<tr>"
				str+="<td>"+val.username+"</td>"//val은 내용
				str+="<td>"+val.content+"</td>"
				str+="<td>"+val.regdate+"</td>"
				if("${principal.user.id}"==val.username){
				str+="<td>"+"<a href='javascript:fdel("+val.comquestionID+")'>삭제</a>"+"</td>"
				}
				str+="</tr>"
			})//each
			str+="</table>"
			$("#replyResult").html(str);
		})//done
	}
	
	function fdel(cnum){
		if(!confirm('정말 댓글을 삭제할까요?')){
			return false;
		}
		$.ajax({
			type:'DELETE',
			url:"/reply/delete/"+comquestionID
		})//ajax
		.done(function(resp){
			alert(resp+"번 댓글 삭제 완료")
			init();
		})
		.fail(function(e){
			alert("댓글 삭제 실패"+e)
		})
	}
	
	$("#BtnComment").click(function(){
		/* if(${empty principal.user}){
			alert("로그인하세요")
			location.href="/login";
			return;
		}
		if($("#msg").val()==""){
			alert("댓글을 작성하세요")
			return;
		}
		var data={
				"questionID":$("#comquestionID").val(),
				"content":$("#msg").val()
		} */
		$.ajax({
			type:"POST",
			url:"/reply/insert/"+$("#comquestionID").val(),
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
		})
		.done(function(resp,status){
			alert(status)
			alert("댓글 추가 성공")
			init();
		})
		.fail(function(){
		alert("댓글추가실패")
		})
	})
	
	
	$("#btnUpdate").click(function(){
		if(confirm("정말 수정할까요?")){
			location.href="/board/boardQuestionupdate/${board.questionID}"
		}
	})
	
	$("#btnDelete").click(function(){
		if(!confirm("정말 삭제 할까요?"))return
		$.ajax({
			type:"delete",
			url:"/board/bqdelete/${board.questionID}",
			success:function(resp){
				if(resp=="success"){
					alert("삭제성공")
					location.href="/board/boardQuestion"
				}
			},
			error:function(e){
				alert("삭제실패"+e)
			}
		})//ajax
	})//btnDelete
	init();
	</script>