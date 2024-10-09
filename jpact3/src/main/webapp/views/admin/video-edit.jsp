<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<form action="${pageContext.request.contextPath }/admin/video/update" method ="post" enctype="multipart/form-data">
  <input type="text" name ="videoid" value="${vide.videoId}" hidden="hidden"> 
  
  <label for="poster">Link Poster:</label><br>
  <input type="text" id="poster" name="poster" value="${vide.poster}" oninput="updateImageFromLink()"><br>
  
  <!-- Hiển thị hình ảnh từ đường link hiện tại -->
  <c:if test="${vide.poster.substring(0,5) != 'https' }">
      <c:url value="/image?fname=${vide.poster}" var="imgUrl"></c:url>
  </c:if> 
  <c:if test="${vide.poster.substring(0,5) == 'https' }">
      <c:url value="${vide.poster}" var="imgUrl"></c:url>
  </c:if>
  <img id="previewImage" height="150" width="200" src="${imgUrl}" />
  
  <label for="lname">Upload File:</label><br>
  <input type="file" id="poster1" name="poster1" accept="image/*" onchange="previewFile()"><br>
  
  <label for="videotitle">Video Title:</label><br>
  <input type="text" id="videotitle" name="videotitle" value="${vide.title}"><br>
  
  <label for="videodescription">Video Description:</label><br>
  <input type="text" id="videodescription" name="videodescription" value="${vide.description}"><br>
  
  <label for="videoview">View Count:</label><br>
  <input type="text" id="videoview" name="videoview" value="${vide.views}"><br>
  
  <label for="videocategory">Category:</label><br> 
  <select id="videocategory" name="videocategory">
    <!-- Hiển thị option mặc định -->
    <option value="">--Chọn Category--</option>
    
    <!-- Lặp qua danh sách Category -->
    <c:forEach var="category" items="${listcate}">
        <!-- Kiểm tra nếu categoryId trùng với categoryId của video hiện tại -->
        <option value="${category.categoryId}" 
            <c:if test="${category.categoryId == vide.category.categoryId}">
                selected="selected"
            </c:if>>
            ${category.categoryname}
        </option>
    </c:forEach>
  </select><br>

  <!-- JavaScript để hiển thị hình ảnh được upload hoặc từ link nhập vào -->
  <script>
    function previewFile() {
      const preview = document.getElementById('previewImage');
      const file = document.getElementById('poster1').files[0];
      const reader = new FileReader();

      reader.onloadend = function () {
        preview.src = reader.result; // Gán hình ảnh đã được upload vào src
      }

      if (file) {
        reader.readAsDataURL(file); // Chuyển đổi file thành URL để hiển thị
      } else {
        // Giữ lại hình ảnh hiện tại nếu không có file được chọn
        preview.src = "${imgUrl}";
      }
    }

    function updateImageFromLink() {
      const linkInput = document.getElementById('poster').value;
      const preview = document.getElementById('previewImage');

      // Nếu link không rỗng, hiển thị ảnh từ link
      if (linkInput) {
        preview.src = linkInput;
      } else {
        preview.src = "${imgUrl}"; // Nếu không có link, giữ hình ảnh cũ
      }
    }
  </script>

  <label for="status">Status:</label><br>
  <input type="radio" id="ston" name="status" value="true" ${vide.active==true?'checked' : '' }><label for="ston">Hoạt động</label><br>
  <input type="radio" id="stoff" name="status" value="false" ${vide.active==false?'checked' : '' }><label for="stoff">Khóa</label><br>
  
  <br><br>
  <input type="submit" value="Update">
</form>