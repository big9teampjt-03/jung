<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/includes/header.jsp"%>

<!-- <div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h1>Spring Board Write</h1>      
    
  </div>
</div> -->
<div class="container">
<br>
<h3>자유게시판 글쓰기</h3>
<br>
<form action = "boardQuestioninsert" method="post"  enctype="multipart/form-data">
<div class="form-group">
      <label for="title">제목</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" >
    </div>
    <div class="form-group">
      <label for="username">글쓴이</label>
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username"value="<sec:authentication property="principal.username"/>" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="content">내용</label>
      <textarea class="form-control" id="content" placeholder="Enter content" name="content"></textarea>
    </div>
    <div class="form-group">
      <label for="uploadquestion">파일</label>
      <input type="file" class="form-control" id="uploadquestion" placeholder="Enter upload" name="uploadquestion" >
    </div>
     <div class="form-check form-check-inline mt-3">
    <input class="form-check-input" type="checkbox" name="secret" id="secret">
    <label class="form-check-label">비밀글 설정</label>
</div>
 <%-- <c:if test="${board.secret == true}">
    <c:choose>
        <c:when test="${board.username eq member.vo.userid || member.authorities eq '[ROLE_ADMIN, ROLE_MEMBER]'}"> <!-- 작성자이거나 관리자일 때 -->
            <td><a href="get${pageMaker.cri.listLink}&counselID=${board.counselID}" class="text-secondary text-center"><i class="icofont-lock"></i><c:out value="${board.title}"/><span class="text-muted small"> [${board.replycnt}]</span></a></td>
        </c:when>
        <c:otherwise>
            <td class="text-secondary"><i class="icofont-lock"></i><c:out value="${board.title}"/><span class="text-muted small"> [${board.replycnt}]</span></td>
        </c:otherwise>
    </c:choose>                                            
</c:if> --%>
    <button type="submit" class="btn btn-primary">글쓰기</button>
    </form>
 </div>