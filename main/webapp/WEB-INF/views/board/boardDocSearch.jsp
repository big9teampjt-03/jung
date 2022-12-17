<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<link rel="stylesheet" href="/css/style.css">

<div class="container mt-5">
	<h2>
		<a href="/board/doctorlist">전문의	찾기</a>
	</h2>

<!-- Doctor Profile No.1 -->
	<div class="page-content page-container" id="page-content">
		<div class="padding">
			<div class="row container d-flex justify-content-start">
			
				<div class="col-xl-80 col-md-40">
					<div class="card user-card-full">
						<div class="row m-l-0 m-r-0">
							<div class="col-sm-4 bg-c-lite-green user-profile">
								<div class="card-block text-center text-white">
									<div class="m-b-25">
										<img src="https://img.icons8.com/color/96/null/veterinary-examination.png" 
											class="img-radius" alt="User-Profile-Image">
									</div>
									<h6 class="f-w-600">헤아림동물병원</h6>
									<p>원장 김00</p>
									<i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="card-block">
									<h6 class="m-b-20 p-b-5 b-b-default f-w-600">연락처</h6>
									<div class="row">
										<div class="col-sm-3">
											<p class="m-b-10 f-w-600">전화번호</p>
											<h6 class="text-muted f-w-400">051-555-1125</h6>
										</div>
										<div class="col-sm-4">
											<p class="m-b-10 f-w-600">이메일</p>
											<h6 class="text-muted f-w-400">00kim@gmail.com</h6>
										</div>
										<div class="col-sm-5">
											<p class="m-b-10 f-w-600">주소</p>
											<h6 class="text-muted f-w-400">
											<a href="/board/docMaps" onclick="window.open(this.href,'지도 보기','width=600, height=500'); return false;">
											부산광역시 동래구 낙민동 205-12</a></h6>
										</div>
									</div>
									
									<h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600">진료 시간</h6>
									<div class="row">
										<div class="col-sm-4">
											<p class="m-b-10 f-w-600">월-금</p>
											<h6 class="text-muted f-w-400">오전 10시-오후 7시<br/>(점심시간 오후 1시-2시)</h6>
										</div>
										<div class="col-sm-4">
											<p class="m-b-10 f-w-600">토</p>
											<h6 class="text-muted f-w-400">오전 10시-오후5시<br/>(점심시간 오후 1시-2시)</h6>
										</div>
										<div class="col-sm-4">
											<p class="m-b-10 f-w-600">일</p>
											<h6 class="text-muted f-w-400">휴진</h6>
										</div>
									</div>
									
									<h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600">소개글</h6>
									<div class="row">
										<div class="col-sm-12">
											<p class="text-muted f-w-400" style="text-indent:20px">
											헤아림 동물병원은 부산 동래구에 위치한 반려동물 주치의 시스템의 동물병원입니다.
											일상 생활 중 아픈 증상을 보이거나, 건강검진, 접종 등의 간단한 진료부터 호르몬, 노령성 질환, 정형 질환, 종양 등 심화 진료 모두 진료 가능합니다.
											여러 2차 동물병원과 연계되어 있으며, 진단 후 연계 병원으로 전원 도와드릴 수 있습니다. </p>
											<p class="text-muted f-w-400" style="text-indent:20px"> 믿고 맡겨주셔서 감사합니다.</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
								</div>
							</div>
						</div>
					</div>





					<%@ include file="../includes/footer.jsp"%>