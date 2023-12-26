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
					<jsp:include page="../include/input/commentInput.jsp">
						<jsp:param value="${video.getV_id()}" name="v_id" />
					</jsp:include>
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
						<div class="d-flex flex-start mb-4">

							<c:choose>
								<c:when
									test="${not empty comment.getC_member().getM_profile_img()}">
									<img class="rounded-circle shadow-1-strong"
										src="<c:url value="/libraryProfiles/${comment.getC_member().getM_profile_img()}" />"
										alt="avatar" width="65" height="65" />
								</c:when>
								<c:otherwise>
									<img class="rounded-circle shadow-1-strong"
										src="<c:url value="/resources/blank_pic.png" />" alt="avatar"
										width="65" height="65" />
								</c:otherwise>
							</c:choose>

							<div class="card w-100">
								<div class="card-body p-4">
									<div>
										<h5>
											<c:out value="${comment.getC_member().getM_firstname()}" />
											<c:out value="${comment.getC_member().getM_lastname()}" />
										</h5>
										<p>
											<c:out value="${comment.getC_content()}" />
										</p>

										<div class="d-flex justify-content-between align-items-center">
											<div class="d-flex align-items-center">
												<!--  
						                    <a href="#!" class="link-muted me-2"><i class="fas fa-thumbs-up me-1"></i>132</a>
						                    <a href="#!" class="link-muted"><i class="fas fa-thumbs-down me-1"></i>15</a>
						                    -->
											</div>
											<jsp:include page="../include/modal/commentModal.jsp">
												<jsp:param value="${comment.getC_id()}" name="c_id" />
												<jsp:param value="${comment.getC_member().getM_id()}"
													name="c_m_id" />
												<jsp:param value="${member.getM_id()}" name="m_id" />
												<jsp:param value="${comment.getC_content()}"
													name="c_content" />
												<jsp:param value="${member}" name="member" />
												<jsp:param value="${video.getV_id()}" name="v_id" />
											</jsp:include>
										</div>

										<!-- Child Comment Input -->
										<div>
											<c:choose>
												<c:when test="${not empty member}">
													<div class="mt-3">
														<form
															action="<c:url value="/comment/child/create/${comment.getC_id()}/${video.getV_id()}" />"
															method="post">
															<div class="form-floating">
																<textarea name="c_content" class="form-control"
																	placeholder="Leave a comment here"
																	id="floatingTextarea2" style="height: 100px"></textarea>
																<label for="floatingTextarea2">Comments</label>
															</div>
															<div
																class="d-flex justify-content-between align-items-center">
																<div class="d-flex align-items-center"></div>
																<div class="mt-3">
																	<button class="btn btn-sm"
																		style="background: #1ce783; color: white;"
																		type="submit">
																		<strong> REPLY </strong>
																	</button>
																</div>
															</div>
														</form>
													</div>
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>
										</div>

										<!-- Child Comment -->
										<div>
											<c:forEach items="${comment.getComments()}"
												var="childComment" varStatus="childStatus">
												<div class="d-flex flex-start mt-4">
													<a class="me-3" href="#"> <c:choose>
															<c:when
																test="${not empty childComment.getC_member().getM_profile_img()}">
																<img class="rounded-circle shadow-1-strong"
																	src="<c:url value="/libraryProfiles/${childComment.getC_member().getM_profile_img()}" />"
																	alt="avatar" width="65" height="65" />
															</c:when>
															<c:otherwise>
																<img class="rounded-circle shadow-1-strong"
																	src="<c:url value="/resources/blank_pic.png" />"
																	alt="avatar" width="65" height="65" />
															</c:otherwise>
														</c:choose>



													</a>
													<div class="flex-grow-1 flex-shrink-1">
														<div>
															<div
																class="d-flex justify-content-between align-items-center">
																<p class="mb-1">
																	<c:out
																		value="${childComment.getC_member().getM_firstname()}" />
																	<c:out
																		value="${childComment.getC_member().getM_lastname()}" />
																</p>
															</div>
															<p class="small mb-0">
																<c:out value="${childComment.getC_content()}" />
															</p>
														</div>
													</div>

													<jsp:include page="../include/modal/childCommentModal.jsp">
														<jsp:param name="child_c_id"
															value="${childComment.getC_id()}" />
														<jsp:param name="child_c_m_id"
															value="${childComment.getC_member().getM_id()}" />
														<jsp:param name="m_id" value="${member.getM_id()}" />
														<jsp:param name="v_id" value="${video.getV_id()}" />
														<jsp:param name="chilc_c_content"
															value="${childComment.getC_content()}" />
														<jsp:param name="member" value="${member}" />
													</jsp:include>

												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>


</body>
</html>