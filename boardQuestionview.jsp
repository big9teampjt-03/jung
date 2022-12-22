<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="../includes/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<input type="hidden" name="questionID" id="questionID" value="${board.questionID }" />
	 <div class="container"> 
    <table class="table">
					<br><br>
					<div>
						<div><h3 class="title ub-word">
						<span class="title_headtext">${board.title }</span>
						</h3>
						</div>
						</div>			
						<hr>
						<div>
						<div class="writer ub-writer" data-nick="작성자" data-uid="" data-loc="view">
							<div class="fl">
								<span class='nickname' title='작성자'>작성자:<em>${board.user.nickname } | </em></span></span>		
								<span class="date" title="작성일"><em>${board.regdate }</em></span>
							</div>
							<div class="fr">
								<span class="count"><em>조회수: ${board.hitcount }</em></span>
								<span class="comment"><em>댓글수:${board.replycnt }</em></span>
							</div>
							</div>
							<hr>
							<span class="content"><em>${board.content }</em></span>
							</div>
							<div>
							<img class="card-img-top" src="/resources/img/${board.questionimage }" style="width:50%">
						</div>
				
<c:if test="${principal.user.username==board.user.username}">
	<button type="button" class="btn btn-primary"  id="btnUpdate">수정</button>
		<button type="button" class="btn btn-secondary" id="btnDelete">삭제</button>
		</c:if> 
	</div>
	
	<br/><br/>
	
	<div class="container mt-5">
		<div class="form-group">
			<label for="comment">Comment:</label>
			<textarea class="form-control" rows="5" id="msg" name="text"></textarea>
		</div>
		
		<button type="button" class="btn btn-success" id="BtnComment">댓글쓰기</button>
	
	<div class="mt-5">댓글(${board.replycnt })</div>
	
	<div id="replyResult"></div>
	</div>
	
	<script>
	var init=function(){
		$.ajax({
			type:"get",
			url:"/reply/qclist/"+$("#questionID").val()
		})
		.done(function(resp){
			var str="<table class='table table-hover'>"
			$.each(resp,function(key,val){
				str+="<tr>"
				str+="<td>"+val.username+"</td>"
				str+="<td>"+val.content+"</td>"
				str+="<td>"+val.regdate+"</td>"
				if("${principal.user.username}"==val.username){
				str+="<td>"+"<a href='javascript:fdel("+val.comquestionID+")'>삭제</a>"+"</td>"
				}
				str+="</tr>"
			})//each
			str+="</table>"
			$("#replyResult").html(str);
		})//done
	}
	
	function fdel(comquestionID){
		if(!confirm('정말 댓글을 삭제할까요?')){
			return false;
		}
		$.ajax({
			type:'DELETE',
			url:"/reply/qdelete/"+comquestionID
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
		 if(${empty principal.user}){
			alert("로그인하세요")
			location.href="/login";
			return;
		}
		if($("#msg").val()==""){
			alert("댓글을 작성하세요")
			return;
		}
		var data={
				//"questionID":$("#comquestionID").val(),
				"content":$("#msg").val()
		} 
		$.ajax({
			type:"POST",
			url:"/reply/qcommentInsert/"+$("#questionID").val(),
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
		})
		.done(function(resp){
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
<%@ include file="../includes/footer.jsp"%>