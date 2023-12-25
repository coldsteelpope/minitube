<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
</head>
<body style="background-color: #0b0c0f;">

	<jsp:include page="../include/navBar.jsp"></jsp:include>

	<!-- Video Section -->
	<section class="mt-4">
		<jsp:include page="../include/video/video.jsp"></jsp:include>
	</section>

	<!-- Video Intro Section -->
	<section class="mt-3">
		<div class="container">
			<h4 style="color: white;">
				<span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>VIDEO
					INFO</strong>
			</h4>
		</div>
		<jsp:include page="../include/video/videoIntro.jsp"></jsp:include>
	</section>

	<!-- Like Button Section -->
	<section class="container mt-3">
		<jsp:include page="../include/btn/likeBtn.jsp"></jsp:include>
	</section>

	<!-- Comment Input Section -->
	<section class="container mt-3">
		<div>
			<h4 style="color: white;">
				<span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>COMMENTS</strong>
			</h4>
		</div>
		<c:choose>
			<c:when test="${not empty member}">
				<div class="mt-3">
					<jsp:include page="../include/input/commentInput.jsp"></jsp:include>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</section>

	<!-- Comment & Child Comment Section -->
	<section>
		<div class="container my-5 py-2 text-dark">
			<div class="row d-flex justify-content-center">
				<div class="col">
					<c:forEach items="${comments}" var="comment" varStatus="status">
						<jsp:include page="../include/comment/commentCard.jsp"></jsp:include>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>


</body>
</html>