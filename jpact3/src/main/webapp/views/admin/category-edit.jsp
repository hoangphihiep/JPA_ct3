<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<form action="${pageContext.request.contextPath }/admin/category/update" method ="post" enctype="multipart/form-data">
  <input type="text" name ="categoryid" value="${cate.categoryId}" hidden="hidden"> 
  
  <label for="categoryname">Category name:</label><br>
  <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>
  
  <label for="lname">Link Images:</label><br>
  <input type="text" id="images" name="images" value="${cate.images}" oninput="updateImageFromLink()"><br>

  <!-- Hiển thị hình ảnh từ đường link hiện tại -->
  <c:if test="${cate.images.substring(0,5) != 'https' }">
      <c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
  </c:if> 
  <c:if test="${cate.images.substring(0,5) == 'https' }">
      <c:url value="${cate.images }" var="imgUrl"></c:url>
  </c:if>
  <img id="previewImage" height="150" width="200" src="${imgUrl}" />

  <label for="lname">Upload Images:</label><br>
  <input type="file" id="images1" name="images1" accept="image/*" onchange="previewFile()"><br>

  <!-- JavaScript để hiển thị hình ảnh được upload hoặc từ link nhập vào -->
  <script>
    function previewFile() {
      const preview = document.getElementById('previewImage');
      const file = document.getElementById('images1').files[0];
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
      const linkInput = document.getElementById('images').value;
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
  <input type="radio" id="ston" name="status" value="1" ${cate.status==1?'checked' : '' }><label for="ston">Hoạt động</label><br>
  <input type="radio" id="stoff" name="status" value="0" ${cate.status==0?'checked' : '' }><label for="stoff">Khóa</label><br>
  
  <br><br>
  <input type="submit" value="Update">
</form>
