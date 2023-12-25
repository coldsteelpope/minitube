<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="d-flex flex-start mb-4">
	<img class="rounded-circle shadow-1-strong me-3"
		src="<c:url value="/libraryProfiles/${comment.getC_memberVo().getM_profile_img()}" />"
		alt="name" width="65" height="65" />

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
					<jsp:include page="../modal/commentModal.jsp"></jsp:include>
				</div>

				<!-- Child Comment -->
				<div>
					<jsp:include page="../input/childCommentInput.jsp"></jsp:include>
				</div>

				<!-- Child Comment -->
				<div>
					<jsp:include page="../comment/childComment.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>