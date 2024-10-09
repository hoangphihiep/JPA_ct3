<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<a href="/jpact3/admin/video/add">Add Video</a><br>
<hr>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Video ID</th>
		<th>Images</th>
		<th>Video Title</th>
		<th>Description</th>
		<th>View</th>
		<th>Category</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listvideo}" var="video" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1}</td>
			<td>${video.videoId}</td>
			<td>
				<c:if test="${video.poster.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
				</c:if> 
				<c:if test="${video.poster.substring(0,5) == 'https' }">
					<c:url value="${video.poster}" var="imgUrl"></c:url>
				</c:if>
				<img height="150" width="200" src="${imgUrl}" />
			</td>
			<td>${video.title}</td>
			<td>${video.description}</td>
			<td>${video.views}</td>
			<td>${video.category.categoryname}</td>
			<td>
			<c:if test="${video.active == true}">
				<span>Hoạt động</span>
			</c:if>
			<c:if test="${video.active != true}">
				<span>Khóa</span>
			</c:if>
			</td>
			<td>
				<a
				href="<c:url value='/admin/video/edit?id=${video.videoId }'/>"
				class="center">Sửa</a> | <a
				href="<c:url value='/admin/video/delete?id=${video.videoId }'/>"
				class="center">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>