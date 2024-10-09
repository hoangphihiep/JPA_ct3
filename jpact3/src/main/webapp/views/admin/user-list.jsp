<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<a href="${pageContext.request.contextPath }/admin/user/add">Add User</a><br>
<hr>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>ID</th>
		<th>Images</th>
		<th>User Name</th>
		<th>Full Name</th>
		<th>Creat Date</th>
		<th>Phone</th>
		<th>Email</th>
		<th>Password</th>
		<th>Role Name</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listuser}" var="user" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1}</td>
			<td>${user.id}</td>
			<td>
				<c:if test="${user.images.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${user.images }" var="imgUrl"></c:url>
				</c:if> 
				<c:if test="${user.images.substring(0,5) == 'https' }">
					<c:url value="${user.images}" var="imgUrl"></c:url>
				</c:if>
				<img height="150" width="200" src="${imgUrl}" />
			</td>
			<td>${user.username}</td>
			<td>${user.fullname}</td>
			<td>${user.creatDate}</td>
			<td>${user.phone}</td>
			<td>${user.email}</td>
			<td>${user.password}</td>
			<td>${user.role.rolename}</td>
			<td>
				<a
				href="<c:url value='/admin/user/edit?id=${user.id }'/>"
				class="center">Sửa</a> | <a
				href="<c:url value='/admin/user/delete?id=${user.id }'/>"
				class="center">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>