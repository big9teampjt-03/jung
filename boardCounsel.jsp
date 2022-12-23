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
<h3>전문의 상담 게시판</h3>
  <table class="table table-hover">
   <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>작성일</th>
        <th>댓글수</th>
      </tr>
    <tbody>
    <c:forEach items="${clists.content }" var="list">
    <tr>
       <c:if test="${list.secret == true}">
       <td>${list.counselID }</td>
    <c:choose>
        <c:when test="${list.user.username eq principal.user.username || user.authorities eq '[ADMIN, USER, DOCTOR]'}"> <!-- 작성자이거나 관리자,의사일 때 -->
            <td><a href="boardCounselview/${list.counselID}" ><i class="icofont-lock"></i><c:out value="${list.title}"/></a></td>
        <td>${list.user.nickname }</td>
            <td>${list.hitcount }</td>
            <td>${list.regdate }</td>
            <td>${list.replycnt}</td>
        </c:when>
        <c:otherwise>
            <td class="text-secondary"><i class="icofont-lock"></i><c:out value="${list.title}"/></td>
             <td>${list.user.nickname }</td>
            <td>${list.hitcount }</td>
            <td>${list.regdate }</td>
            <td>${list.replycnt}</td>
        </c:otherwise>
    </c:choose>                                            
</c:if> 
</tr>
<tr>
       <c:if test="${list.secret == false}"><td>${list.counselID }</td>
        <td><a href="boardCounselview/${list.counselID}"><i class="icofont-lock"></i>${list.title }</a></td>
        <td>${list.user.nickname }</td>
            <td>${list.hitcount }</td>
            <td>${list.regdate }</td>
            <td>${list.replycnt}</td>
    </c:if> 
    </tr>
      </c:forEach> 
    </tbody>
   <p align="right">
     <a href="/board/boardCounselinsert"><button class="btn btn-primary">글쓰기</button></a>
    </p> 
     </table>
    
  <div class="d-flex justify-content-between">
    <ul class="pagination mt-3">

			<!-- 이전 -->
			<c:if test="${clists.first==false}">
				<li class="page-item"><a class="page-link"
					href="?page=${lists.number-1}&field=${param.field}&word=${param.word}">이전</a></li>
				<!-- 페이지 현재 번호는 number -->
			</c:if>
			
			<!-- 다음 -->
			<c:if test="${clists.last==false}">
				<li class="page-item"><a class="page-link"
					href="?page=${lists.number+1}&field=${param.field}&word=${param.word}">다음</a></li>
			</c:if>
			
		</ul>
	<form class="form-inline" action="/board/boardCounsel" method="get">
			<select name='field' id="field" class="form-control mr-sm-1">
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select> <input type='text' name='word' class="form-control"
				placeholder="Search">
			<button class='btn btn-secondary'>Search</button>
		</form>	
</div>
</div>

</body>
</html>
<%@ include file="../includes/footer.jsp"%>