<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@include file="../includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<div class="container">
<br>
<h3>자유 게시판</h3>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>작성일</th>
        <th>댓글수</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${qlists.content }" var="list">
      <tr>
        <td>${list.questionID}</td>
		<td><a href="/board/boardQuestionview/${list.questionID}">${list.title}</td>
        <td>${list.username}</td>
		<td>${list.hitcount}</td>
		<td>${list.regdate}</td>
		<td>${list.replycnt}</td>
      </tr>    
      </c:forEach> 
    </tbody>
     <p align="right">
     <a href="/board/boardQuestioninsert"><button class="btn btn-primary">글쓰기</button></a>
    </p>
    </table>
    
    <ul class="pagination mt-3">

			<!-- 이전 -->
			<c:if test="${qlists.first==false}">
				<li class="page-item"><a class="page-link"
					href="?page=${lists.number-1}&field=${param.field}&word=${param.word}">이전</a></li>
				<!-- 페이지 현재 번호는 number -->
			</c:if>
			
			<!-- 다음 -->
			<c:if test="${qlists.last==false}">
				<li class="page-item"><a class="page-link"
					href="?page=${lists.number+1}&field=${param.field}&word=${param.word}">다음</a></li>
			</c:if>
		</ul>
</div>

</body>
</html>