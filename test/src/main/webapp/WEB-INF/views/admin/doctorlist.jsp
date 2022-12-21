<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>

<div class="container mt-3">
	<h3>전문가 회원 리스트</h3>
	<table class="table table-hover" style="text-align: center;">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>가입일</th>
				<th>승인상태</th>
				<th>관리</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${lists}" var="doc">
				<tr>
					<td><a href="docdetail/${doc.user.userid}"> ${doc.user.username }</a></td>
					<td>${doc.docname }</td>
					<td>${doc.regdate }</td>
					<td>${doc.approval }</td>
				<c:choose>
			  	<c:when test="${doc.approval == 0}">
			  	<td>
	                <button data-doctorid ="${doc.doctorid}" type="button" value="approve" id="approve">승인</button>
	                <button data-doctorid ="${doc.doctorid}" type="button" value="deny" id="deny">거부</button>
                </td>
			  	</c:when>
			  	<c:when test="${doc.approval == 1}">
			  	<td>
	                <button data-doctorid ="${doc.doctorid}" type="button" value="approve" id="drop">삭제</button>
                </td>
			  	</c:when>
			  	<c:when test="${doc.approval == 3}">
			  	 	<td>추방회원</td>
			  	</c:when>
			  	<c:when test="${doc.approval == 2}">
			  		<td>승인거절회원</td>
			  	</c:when>
			  </c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script>
$("#approve").click(function(){
	var dataParam = {
			"doctorid"
			"approval":$("#approval").val()
	}
  	// const success = $(this).data("doctorid");
	// console.log(success);
	// 0 승인대기, 1 승인, 2 거절 3 추방	
	$.ajax({
		type : "post",
		url : "/admin/success",
		data : JSON.stringify(dataParam)
	}).done(function(resp) {
		if (resp == "success") {
			alert("승인 완료")
			location.href="/admin/doctorlist"
		}
	})
	.fail(function(){
		alert("승인 오류")
	})
})
//     	success : function(resp){
//     	},error : function(status, error) {
// 			console.log('에러발생');
// 			console.log(status, error);
//		}
//	});
//   $('.modal_approve').fadeIn(500);
//   });

  $("#deny").click(function(){
    console.log('승인거부');
  })
  
  $("#drop").click(function() {
	  var dataParam = {
				"approval":$("#approval").val()
		}
	  // const drop = $(this).data("doctorid");
      // console.log(drop);
       	$.ajax({
    		type : "post",
    		url : "/admin/drop",
    		data : JSON.stringify(dataParam)
        	}).done(function(resp){
        		if (resp == "success") {
        			alert("승인 완료")
        			location.href="/admin/doctorlist"
        		}
        	})
        	.fail(function(){
        		alert("승인 오류")
        	})
  })
//         	success : function(resp){
//         	},error : function(status, error) {
//   				console.log('에러발생');		
//   				console.log(status, error);
//   			}
</script>
<%@include file="../includes/footer.jsp"%>
