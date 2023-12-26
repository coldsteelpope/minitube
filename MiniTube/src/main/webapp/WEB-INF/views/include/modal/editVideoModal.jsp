<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Button trigger modal -->
<button type="button" class="btn btn-lg btn-success w-100 mt-3"
	data-bs-toggle="modal"
	data-bs-target="#staticBackdrop${param.v_id}">EDIT</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop${param.v_id}"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="staticBackdropLabel">${param.v_title}</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form
				action="<c:url value="/video/edit/" />${param.v_id}"
				method="post" enctype="multipart/form-data">
				<div class="modal-body">
					<div>
						<label style="border-left: 5px solid #1ce783;" for="title_label"><strong
							class="ms-1">TITLE</strong></label> <input required id="title_label"
							type="text" name="v_title" placeholder="title"
							class="form-control mt-2" value="${param.v_title}" />
					</div>
					<div class="mt-2">
						<label style="border-left: 5px solid #1ce783;"
							for="description_label"><strong class="ms-1">DESCRIPTION</strong></label>
						<textarea required id="description_label" name="v_description"
							placeholder="description" class="form-control mt-2">${param.v_description}</textarea>
					</div>
					<div class="mt-2">
						<label style="border-left: 5px solid #1ce783;"
							for="thumbnail_label"><strong class="ms-1">THUMBNAIL</strong></label>
						<br /> <input id="thumbnail_label" type="file"
							name="thumbnailFile" class="form-control mt-2" />
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">CLOSE</button>

					<button type="submit" class="btn btn-primary">EDIT</button>
				</div>
			</form>
		</div>
	</div>
</div>