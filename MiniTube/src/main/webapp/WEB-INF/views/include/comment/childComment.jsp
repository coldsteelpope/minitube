<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:forEach items="${comment.getComments()}" var="childComment"
	varStatus="childStatus">
	<div class="d-flex flex-start mt-4">
		<a class="me-3" href="#"> <img
			class="rounded-circle shadow-1-strong"
			src="<c:url value="/libraryProfiles/${childComment.getC_member().getM_profile_img()}" />"
			alt="avatar" width="65" height="65" />
		</a>
		<div class="flex-grow-1 flex-shrink-1">
			<div>
				<div class="d-flex justify-content-between align-items-center">
					<p class="mb-1">
						<c:out value="${childComment.getC_member().getM_firstname()}" />
						<c:out value="${childComment.getC_member().getM_lastname()}" />
					</p>
				</div>
				<p class="small mb-0">
					<c:out value="${childComment.getC_content()}" />
				</p>
			</div>
		</div>
		<jsp:include page="../modal/childCommentModal.jsp"></jsp:include>
	</div>
</c:forEach>