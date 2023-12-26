<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
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

	<section class="vh-30 mt-4">
		<div class="container py-5">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-md-12 col-xl-4">
					<div class="card" style="border-radius: 15px;">
						<div class="card-body text-center">
							<div class="mt-3 mb-4">
							
								<c:choose>
									<c:when test="${not empty profileMember.getM_profile_img()}">
										<img
										src=<c:url value="/libraryProfiles/${profileMember.getM_profile_img()}" />
										class="rounded-circle img-fluid"
										style="width: 100px; height: 100px;" />
									</c:when>
									<c:otherwise>
										<img
										src=<c:url value="/resources/blank_pic.png" />
										class="rounded-circle img-fluid"
										style="width: 100px; height: 100px;" />
									</c:otherwise>
								</c:choose>

									
							</div>
							<h4 class="mb-2">
								<strong>${profileMember.getM_lastname()}
									${profileMember.getM_firstname()}</strong>
							</h4>

							<c:choose>
								<c:when
									test="${isMe eq true}">
									<div class="mt-2">
										<button type="button" class="btn btn-success btn-rounded"
											onclick="location.href='<c:url value="/member/uploadVideo" />'">
											UPLOAD VIDEO</button>
										<button type="button"
											class="btn btn-outline-success btn-rounded"
											onclick="location.href='<c:url value="/member/manage" />'">
											MANGE PROFILE</button>
									</div>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="mt-4 mb-5">
		<div class="container mt-3">
			<div>
				<h4 style="color: white;">
					<span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>${member.getM_firstname()}
						${profileMember.getM_lastname()}'S VIDEOS</strong>
				</h4>
			</div>
			<jsp:include page="../include/video/videos.jsp"></jsp:include>
		</div>
	</section>

</body>
</html>