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
<h3>상담글쓰기</h3>
<br>
<form action = "insert" method="post">
<div class="form-group">
      <label for="title">제목</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" >
    </div>
    <div class="form-group">
      <label for="writer">글쓴이</label>
      <input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer"  <%-- value="<sec:authentication property="principal.user.username"/>" --%> readonly="readonly">
    </div>
    <div class="form-group">
      <label for="content">내용</label>
      <textarea class="form-control" id="content" placeholder="Enter content" name="content"></textarea>
    </div>
    <div class="form-group">
      <label for="upload">파일</label>
      <input type="file" class="form-control" id="upload" placeholder="Enter upload" name="upload" >
    </div>
    <button type="submit" class="btn btn-primary">글쓰기</button>
    </form>
 </div>