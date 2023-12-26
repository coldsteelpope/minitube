<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when
		test="${not empty param.member && param.child_c_m_id == param.m_id}">

		<div class="modal fade"
			id="exampleModal${param.child_c_id}" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
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
						action="<c:url value="/comment/edit/${param.child_c_id}/${param.v_id}" />"
						method="post">
						<div class="modal-body">
							<textarea name="c_content" class="form-control"><c:out value="${param.chilc_c_content}" /></textarea>
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

		<div class="d-flex">
			<div>
				<button type="button" class="btn btn-sm btn-primary"
					data-bs-toggle="modal"
					data-bs-target="#exampleModal${param.child_c_id}">EDIT</button>
			</div>
			<div>
				<form
					action="<c:url value="/comment/child/delete/${param.child_c_id}/${param.v_id}" />"
					method="post">
					<button class="btn btn-sm btn-danger ms-3" type="submit">
						DELETE</button>
				</form>
			</div>
		</div>

	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>






