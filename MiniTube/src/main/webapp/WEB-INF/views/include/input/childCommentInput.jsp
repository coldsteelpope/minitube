<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Child Comment Input -->
<c:choose>
	<c:when test="${not empty member}">
		<div class="mt-3">
			<form
				action="<c:url value="/comment/child/create/${comment.getC_id()}/${video.getV_id()}" />"
				method="post">
				<div class="form-floating">
					<textarea name="c_content" class="form-control"
						placeholder="Leave a comment here" id="floatingTextarea2"
						style="height: 100px"></textarea>
					<label for="floatingTextarea2">Comments</label>
				</div>
				<div class="d-flex justify-content-between align-items-center">
					<div class="d-flex align-items-center"></div>
					<div class="mt-3">
						<button class="btn btn-sm"
							style="background: #1ce783; color: white;" type="submit">
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