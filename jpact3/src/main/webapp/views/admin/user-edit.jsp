<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<form action="${pageContext.request.contextPath }/admin/user/update" method ="post" enctype="multipart/form-data">
  <input type="text" name ="userid" value="${use.id}" hidden="hidden"> 
  
  <label for="lname">Link Image:</label><br>
  <input type="text" id="image" name="image" value="${use.images}" oninput="updateImageFromLink()"><br>	

  <!-- Hiển thị hình ảnh từ đường link hiện tại -->
  <c:if test="${use.images.substring(0,5) != 'https' }">
      <c:url value="/image?fname=${use.images}" var="imgUrl"></c:url>
  </c:if> 
  <c:if test="${use.images.substring(0,5) == 'https' }">
      <c:url value="${use.images}" var="imgUrl"></c:url>
  </c:if>
  <img id="previewImage" height="150" width="200" src="${imgUrl}" />
  
  <label for="lname">Upload Image:</label><br>
  <input type="file" id="image1" name="image1" accept="image/*" onchange="previewFile()"><br>
  
  <label for="username">User Name:</label><br>
  <input type="text" id="username" name="username" value="${use.username}"><br>
  
  <label for="fullname">Full Name:</label><br>
  <input type="text" id="fullname" name="fullname" value="${use.fullname}"><br>
  
  <label for="creatdate">Creat Date:</label><br>
  <input type="date" id="creatdate" name="creatdate" value="${use.creatDate}"><br>
  
  <label for="phone">Phone:</label><br>
  <input type="text" id="phone" name="phone" value="${use.phone}"><br>
  
  <label for="email">Email:</label><br>
  <input type="text" id="email" name="email" value="${use.email}"><br>
  
  <label for="password">Password:</label><br>
  <input type="text" id="password" name="password" value="${use.password}"><br>
  
  <label for="rolename">Role:</label><br> 
  <select id="rolename" name="rolename">
  <option value="">--Chọn Role--</option>
  <c:forEach var="role" items="${listrole}">
      <option value="${role.roleId}" 
            <c:if test="${role.roleId == use.role.roleId}">
                selected="selected"
            </c:if>>
            ${role.rolename}
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
  <br><br>
  <input type="submit" value="Update">
</form>