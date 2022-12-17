<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>

<div class="container">
	<h3>Doctor JOIN</h3>
	<div class="form-group">
		<label for="profileimage">프로필 사진:</label> <input type="file"
			 id="profileimage" placeholder="사진을 등록해주세요."
			name="profileimage">
	</div>
	<div class="form-group">
		<label for="docname">이름:</label> <input type="text"
			class="form-control" id="docname" placeholder="Enter Name"
			name="docname">
	</div>
	<div class="form-group">
		<label for="username">아이디:</label> <input type="email"
			class="form-control" id="username" placeholder="Enter Email"
			name="username"><input type="button" onclick="idOverlap()" value="중복확인"/>
	</div>
	<div class="form-group">
		<label for="password">패스워드:</label> <input type="password"
			class="form-control" id="password" placeholder="Enter Password"
			name="password">
	</div>
	<div class="form-group">
			<label for="pass_check">패스워드 확인:</label> <input type="password"
				class="form-control" id="pass_check" placeholder="Enter pass_check"
				name="pass_check">
		</div>

	<div class="form-group">
		<label for="doccareer">경력:</label> <input type="file"
			 id="doccareer" placeholder="사진을 등록해주세요."
			name="doccareer">
	</div>
	
	<div class="form-group">
		<label for="doclicensenum">의사면허번호:</label> <input type="text"
			class="form-control" id="doclicensenum" placeholder="면허 번호를 입력해 주세요."
			name="doclicensenum">
	</div>
	<div class="form-group">
		<label for="licenseimage">의사면허사진:</label> <input type="file"
			 id="licenseimage" placeholder="면허 번호를 입력해 주세요."
			name="licenseimage">
	</div>
	<div class="form-group">
		<label for="dochospital">병원 이름:</label> <input type="text"
			class="form-control" id="dochospital" placeholder="병원 이름을 입력해 주세요."
			name="dochospital">
	</div>
	<div class="form-group">
		<label for="dochospitaltel">병원 전화번호:</label> <input type="text"
			class="form-control" id="dochospitaltel" placeholder="병원 전화번호를 입력해 주세요."
			name="dochospitaltel">
	</div>
	<div class="form-group">
		<label for="dochospitaladdr">병원 주소:</label> <input type="text"
			class="form-control" id="dochospitaladdr" placeholder="병원 주소를 입력해 주세요."
			name="dochospitaladdr">
	</div>
	<div class="form-group">
		<label for="dochospitalpostal">병원 우편번호:</label> <input type="text"
			class="form-control" id="dochospitalpostal" placeholder="병원 우편번호를 입력해 주세요."
			name="dochospitalpostal">
	</div>
	<button class="btn btn-secondary" id="btnJoin">회원가입</button>

	<script>
		$("#btnJoin").click(function() {
			if ($("#username").val() == "") {
				alert("이메일 입력하세요")
				$("#username").focus();
				return false;
			}
			if ($("#password").val() == "") {
				alert("비밀번호 입력하세요")
				$("#password").focus();
				return false;
			}
			if ($("#pass_check").val() == "") {
				alert("비밀번호 입력하세요")
				$("#pass_check").focus();
				return false;
			}
// 			if ($("#nick").val() == "") {
// 				alert("닉네임을 입력하세요")
// 				$("#nick").focus();
// 				return false;
// 			}
// 			if ($("#tel").val() == "") {
// 				alert("번호를 입력하세요")
// 				$("#tel").focus();
// 				return false;
// 			}
			var dataParam = {
				"username" : $("#username").val(),
				"password" : $("#password").val(),
				"pass_check" : $("#pass_check").val(),
				"docname": $("#docname").val(),
				"doccareer": $("#doccareer").val(),
				"doclicensenum": $("#doclicensenum").val(),
				"dochospital": $("#dochospital").val(),
				"dochospitaltel": $("#dochospitaltel").val(),
				"dochospitaladdr": $("#dochospitaladdr").val(),
				"dochospitalpostal": $("#dochospitalpostal").val(),
			}
			$.ajax({
				type : "POST",
				url : "/doctor/doctorJoin",
				contentType : "application/json;charset=utf-8",
				data : JSON.stringify(dataParam)
			}).done(function(resp) {
				if (resp == "success") {
					alert("회원가입성공")
					location.href="/doctor/doctorLogin"
				} else if (resp == "fail") {
					alert("이메일 중복")
					$("#email").val("")
				}
			}) //done
			.fail(function() {
				alert("회원가입실패")
			})
		})
		
	</script>
</div>
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>