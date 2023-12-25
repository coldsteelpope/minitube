<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:choose>
	<c:when test="${not empty member && comment.getC_member().getM_id() == member.getM_id()}">
		<div class="modal fade" id="exampleModal${comment.getC_id()}"
			tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">
							<strong>EDIT COMMENT</strong>
						</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form
						action="<c:url value="/comment/edit/${comment.getC_id()}/${video.getV_id()}" />"
						method="post">
						<div class="modal-body">
							<textarea name="c_content" class="form-control"><c:out
									value="${comment.getC_content()}" /></textarea>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save
								changes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="d-flex flex-row">
			<button type="button" class="btn btn-sm btn-primary"
				data-bs-toggle="modal"
				data-bs-target="#exampleModal${comment.getC_id()}">EDIT</button>
			<form
				action="<c:url value="/comment/delete/${comment.getC_id()}/${video.getV_id()}" />"
				method="post">
				<button class="btn btn-sm btn-danger ms-3" type="submit">
					DELETE</button>
			</form>
		</div>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>