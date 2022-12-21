<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<div class="container mt-3">
	<h3>${principal.user.nickname}님의 전문가 정보 등록하기</h3>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div class="form-group">
			<input type="hidden" class="form-control" id="userid" name="userid"
				value="${principal.user.userid }" readonly="readonly">
		</div>

		<div class="form-group">
			<label for="docname">이름(실명):</label> <input type="text"
				class="form-control" id="docname" placeholder="이름(실명)을 입력해 주세요."
				name="docname">
		</div>

		<div class="form-group">
			<label for="doclicensenum">의사 면허 번호:</label> <input type="text"
				class="form-control" id="doclicensenum"
				placeholder="면허 번호를 입력해 주세요." name="doclicensenum">
		</div>

		<div class="form-group">
			<label for="dochospital">병원 이름:</label> <input type="text"
				class="form-control" id="dochospital" placeholder="병원 이름을 입력해 주세요."
				name="dochospital">
		</div>

		<div class="form-group">
			<label for="dochospitaltel">병원 전화번호:</label> <input type="text"
				class="form-control" id="dochospitaltel"
				placeholder="병원 전화번호를 입력해 주세요." name="dochospitaltel">
		</div>

		<div class="form-group">
			<label for="dochospitaladdr">병원 주소:</label> <input type="text"
				class="form-control" id="dochospitaladdr"
				placeholder="병원 주소를 입력해 주세요." name="dochospitaladdr">
		</div>

		<div class="form-group">
			<label for="dochospitalpostal">병원 우편번호:</label> <input type="text"
				class="form-control" id="dochospitalpostal"
				placeholder="병원 우편번호를 입력해 주세요." name="dochospitalpostal">
		</div>

<!-- 		<div class="form-group"> -->
<!-- 			<label for="imagedocprofile">프로필 사진:</label> <input type="file" -->
<!-- 				id="imagedocprofile" placeholder="사진을 등록해 주세요." -->
<!-- 				name="imagedocprofile"> -->
<!-- 		</div> -->

<!-- 		<div class="form-group"> -->
<!-- 			<label for="imagedoclicense">의사 면허증:</label> <input type="file" -->
<!-- 				id="imagedoclicense" placeholder="면허증을 등록해 주세요." -->
<!-- 				name="imagedoclicense"> -->
<!-- 		</div> -->

		<button class="btn btn-info" id="btnDocApproval">승인 요청하기</button>
</div>

<script>
	$("#btnDocApproval").click(function() {
		var dataParam = {
			"user_id":$("#userid").val(),
			"docname" : $("#docname").val(),
			"doclicensenum" : $("#doclicensenum").val(),
			"dochospital" : $("#dochospital").val(),
			"dochospitaltel" : $("#dochospitaltel").val(),
			"dochospitaladdr" : $("#dochospitaladdr").val(),
			"dochospitalpostal" : $("#dochospitalpostal").val(),
		}
		$.ajax({
			type : "POST",
			url : "/docupdate/"+$("#userid").val(),
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(dataParam)
		}).done(function(resp) {
			if (resp == "success") {
				alert("승인 요청되었습니다.")
				location.href = "/"
			}
		}) //done
		.fail(function() {
			alert("승인 요청 오류")
		})
	})
</script>
<%@ include file="includes/footer.jsp"%>