<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<form action="${pageContext.request.contextPath }/admin/video/insert" method ="post" enctype="multipart/form-data">
  <label for="lname">Link Poster:</label><br>
  <input type="text" id="poster" name="poster"><br>				

  <label for="lname">Upload File:</label><br>
  <input type="file" id="poster1" name="poster1"><br>
  
  <label for="videoid">Video ID:</label><br>
  <input type="text" id="videoid" name="videoid"><br>
  
  <label for="videotitle">Video Title:</label><br>
  <input type="text" id="videotitle" name="videotitle"><br>
  
  <label for="videodescription">Video Description:</label><br>
  <input type="text" id="videodescription" name="videodescription"><br>
  
  <label for="videoview">View Count:</label><br>
  <input type="text" id="videoview" name="videoview"><br>
  
  <label for="videocategory">Category:</label><br> 
  <select id="videocategory" name="videocategory">
  <option value="">--Chọn Category--</option>
  <c:forEach var="category" items="${listcate}">
      <option value="${category.categoryId}">${category.categoryname}</option>
  </c:forEach>
  </select><br>
  
  
  <label for="status">Status:</label><br>
  <input type="radio" id="ston" name="status" value="1" ><br>
   <label for="css">Hoạt động</label><br>
  <input type="radio" id="stoff" name="status" value="0" ><br>
  <label for="javascript">Khóa</label>
  
  <br><br>
  <input type="submit" value="Insert">
</form> 