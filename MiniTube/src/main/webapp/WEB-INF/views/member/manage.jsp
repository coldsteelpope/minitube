<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Manage</title>
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
									<c:when test="${not empty member.getM_profile_img()}">
										<img
											src=<c:url value="/libraryProfiles/${member.getM_profile_img()}" />
											class="rounded-circle img-fluid"
											style="width: 100px; height: 100px;" />
									</c:when>
									<c:otherwise>
										<img src=<c:url value="/resources/blank_pic.png" />
											class="rounded-circle img-fluid"
											style="width: 100px; height: 100px;" />
									</c:otherwise>
								</c:choose>


							</div>
							<h4 class="mb-2">
								<strong>${member.getM_lastname()}
									${member.getM_firstname()}</strong>
							</h4>

							<div>
								<form
									action="<c:url value="/member/edit/${member.getM_id()}" />"
									method="post" enctype="multipart/form-data">
									<input required type="file" name="profileImageFile"
										class="form-control" />
									<div class="mt-3 w-100">
										<button class="btn btn-lg text-white w-100" type="submit"
											style="background-color: #1ce783;">
											<strong>Change Profile</strong>
										</button>
									</div>
								</form>
							</div>
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
					<span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>MANAGE
						${member.getM_firstname()} ${member.getM_lastname()}'S VIDEOS</strong>
				</h4>
			</div>
			<div
				class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-3">
				<c:forEach items="${videos}" var="video">
					<div class="col">
						<img class="w-100" style="height: 380px;"
							src=<c:url value="/librarythumbs/${video.getV_thumbnail()}" /> />

						<div class="mt-3">
							<h4 style="color: white">
								<strong>${video.getV_title()}</strong>
							</h4>
						</div>
						<div>
							<!-- Delete Button -->
							<div>
								<jsp:include page="../include/btn/deleteVideoBtn.jsp">
									<jsp:param value="${video.getV_id()}" name="v_id" />
									<jsp:param value="${member.getM_id()}" name="m_id" />
								</jsp:include>
							</div>
							<!-- Edit Button -->
							<div>
								
								<jsp:include page="../include/modal/editVideoModal.jsp">
									<jsp:param value="${video.getV_id()}" name="v_id"/>
									<jsp:param value="${video.getV_title()}" name="v_title" />
									<jsp:param value="${video.getV_description()}" name="v_description" />
									<jsp:param value="${member.getM_id()}" name="m_id" />
								</jsp:include>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>


</body>
</html>