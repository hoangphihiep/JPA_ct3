<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Forgot Password</title>
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f4f4f9;
			margin: 0;
			padding: 0;
			display: flex;
			justify-content: center;
			align-items: center;
			height: 100vh;
		}
		.container {
			background-color: white;
			padding: 30px;
			box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
			border-radius: 8px;
			width: 350px;
		}
		h2 {
			text-align: center;
			color: #333;
		}
		label {
			display: block;
			margin: 10px 0 5px;
			color: #333;
		}
		input[type="text"] {
			width: 100%;
			padding: 10px;
			margin-bottom: 15px;
			border: 1px solid #ccc;
			border-radius: 4px;
			box-sizing: border-box;
		}
		button {
			width: 100%;
			padding: 10px;
			background-color: #4CAF50;
			color: white;
			border: none;
			border-radius: 4px;
			cursor: pointer;
			font-size: 16px;
		}
		button:hover {
			background-color: #45a049;
		}
		.alert {
			color: red;
			margin-bottom: 15px;
			text-align: center;
		}
	</style>
</head>
<body>
	<div class="container">
		<h2>Forgot Password</h2>
		<c:if test="${alert != null}">
			<p class="alert">${alert}</p>
		</c:if>
		<form action="\ltweb_chieut3\forgot-password" method="POST">
			<label for="usernameOrEmail">Username or Email:</label>
			<input type="text" name="usernameOrEmail" required>
			<button type="submit">Submit</button>
		</form>
	</div>
</body>
</html>
