<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/includes/header.jsp" %>
<br>
<div class="container">    
  
<div class="form-group">
 
    <div class="row">
    <div class="col"> 
 <%--    <input type="hidden" name="memberID" id="memberID" value="${doctor.memberID}"> --%>
    <label for="username">doctorID:</label>
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" value="${doctor.username }"  readonly="readonly" >
       </div>
  </div>
    
      <div class="form-group">
      <label for="name">Phone:</label>
      <input type="text" class="form-control" id="phone" placeholder="Enter phone" name="phone" value="${doctor.phone }">
    </div>
     <div class="form-group">
       <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter Password" name="password" value="${doctor.password }" readonly="readonly">
    </div>
     </div>
</div>
<br/>
    <button type="button" class="btn btn-primary" id="btnUpdate">수정</button>	   
    <button type="button" class="btn btn-danger" id="btnDelete">탈퇴</button>	 
</div>

