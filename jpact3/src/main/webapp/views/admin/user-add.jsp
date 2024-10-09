<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<form action="${pageContext.request.contextPath }/admin/user/insert" method ="post" enctype="multipart/form-data">
  <label for="lname">Link Image:</label><br>
  <input type="text" id="image" name="image"><br>				

  <label for="lname">Upload Image:</label><br>
  <input type="file" id="image1" name="image1"><br>
  
  <label for="username">User Name:</label><br>
  <input type="text" id="username" name="username"><br>
  
  <label for="fullname">Full Name:</label><br>
  <input type="text" id="fullname" name="fullname"><br>
  
  <label for="creatdate">Creat Date:</label><br>
  <input type="date" id="creatdate" name="creatdate"><br>
  
  <label for="phone">Phone:</label><br>
  <input type="text" id="phone" name="phone"><br>
  
  <label for="email">Email:</label><br>
  <input type="text" id="email" name="email"><br>
  
  <label for="password">Password:</label><br>
  <input type="text" id="password" name="password"><br>
  
  <label for="rolename">Role:</label><br> 
  <select id="rolename" name="rolename">
  <option value="">--Chọn Role--</option>
  <c:forEach var="role" items="${listrole}">
      <option value="${role.roleId}">${role.rolename}</option>
  </c:forEach>
  </select><br>
  <br><br>
  <input type="submit" value="Insert">
</form> 