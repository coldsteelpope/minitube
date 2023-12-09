<%@page import="com.google.minitube.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>

<%
	MemberVo loginedMemberVo = (MemberVo)session.getAttribute("loginedMemberVo");
%>


<html>
<head>
<meta charset="UTF-8">
<title>Manage</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</head>
<body style="background-color: #0b0c0f;">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" aria-label="Ninth navbar example">
	    <div class="container-xl">
	      <a class="navbar-brand" href="<c:url value="/" />">Container XL</a>
	      <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07XL" aria-controls="navbarsExample07XL" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	
	      <div class="navbar-collapse collapse" id="navbarsExample07XL" style="">
	      
	      	<%
	      	if(loginedMemberVo != null)
	      	{
	      	%>
	      	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
	          <li class="nav-item">
	            <a class="nav-link active" aria-current="page" href="<c:url value="/member/profile" />/<%=loginedMemberVo.getM_id()%>">PROFILE</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="<c:url value="/member/uploadVideo" />">UPLOAD</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="<c:url value="/auth/signoutConfirm" />">LOGOUT</a>
	          </li>
	        </ul>
	      	<%
	      	}
	      	else
	      	{
	      	%>
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
	          <li class="nav-item">
	            <a class="nav-link" href="<c:url value="/auth/signin" />">LOGIN</a>
	          </li>
	        </ul>
	        <%
	      	}
	        %>
	        <form role="search" action="<c:url value="/search" />">
	        	<input class="form-control" type="search" name="v_title" placeholder="search" aria-label="Search" />
	        </form>
	      </div>
	    </div>
  	</nav>
  	
  	
  	
	<section class="vh-30 mt-4">
	  <div class="container py-5">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-md-12 col-xl-4">
	
	        <div class="card" style="border-radius: 15px;">
	          <div class="card-body text-center">
	            <div class="mt-3 mb-4">
	              <img src=<c:url value="${member_img}" />
	                class="rounded-circle img-fluid" style="width: 100px; height: 100px;" />
	            </div>
	            <h4 class="mb-2"><strong>${member.getM_lastname()} ${member.getM_firstname()}</strong></h4>

				<div>
					<form 
						action="<c:url value="/member/edit/profile-image/${member.getM_id()}" />"
						method="post" 
						enctype="multipart/form-data"
					>	
						<input required type="file" name="profileImageFile" class="form-control" />
						<div class="mt-3 w-100">
							<button class="btn btn-lg text-white w-100" type="submit" style="background-color: #1ce783;">
								<strong>Change Profile Image</strong>
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
				<h4 style="color:white;"><span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>MANAGE ${member.getM_firstname()} ${member.getM_lastname()}'S VIDEOS</strong></h4>
			</div>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-3">
				<c:forEach items="${member.getM_videos()}" var="videoEle">
					<div class="col">
						<img class="w-100" style="height: 380px;" src=<c:url value="/librarythumbs/${videoEle.getV_thumbnail()}" /> />

						<div class="mt-3">
							<h4 style="color: white"><strong>${videoEle.getV_title()}</strong></h4>
						</div>
						<div>
							<!-- Delete Button -->
							<div>
								<form action="<c:url value="/video/delete/${videoEle.getV_id()}/${member.getM_id()}" />" method="post">
									<button class="btn btn-lg btn-danger w-100" type="submit">Delete</button>
								</form>
							</div>
							<!-- Edit Button -->
							<div>
							
							
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-lg btn-success w-100 mt-3" data-bs-toggle="modal" data-bs-target="#staticBackdrop${videoEle.getV_id()}">
								  EDIT
								</button>
								
								<!-- Modal -->
								<div class="modal fade" id="staticBackdrop${videoEle.getV_id()}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h1 class="modal-title fs-5" id="staticBackdropLabel">${videoEle.getV_title()}</h1>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
									      <form action="<c:url value="/video/edit/" />${videoEle.getV_id()}/${member.getM_id()}" method="post" enctype="multipart/form-data">
										      <div class="modal-body">
												<div>
													<label style="border-left: 5px solid #1ce783;" for="title_label"><strong class="ms-1">TITLE</strong></label>
													<input required id="title_label" type="text" name="v_title" placeholder="title" class="form-control mt-2" value="${videoEle.getV_title()}" />
												</div>
												<div class="mt-2">
													<label style="border-left: 5px solid #1ce783;" for="description_label"><strong class="ms-1">DESCRIPTION</strong></label>
													<textarea required id="description_label" name="v_description" placeholder="description" class="form-control mt-2">${videoEle.getV_description()}</textarea>
												</div>
												<div class="mt-2">
													<label style="border-left: 5px solid #1ce783;" for="thumbnail_label"><strong class="ms-1">THUMBNAIL</strong></label>
													<br />
													<input id="thumbnail_label" type="file" name="thumbnailFile" class="form-control mt-2"/>
												</div>
										      </div>
										      
										      <div class="modal-footer">
										        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">CLOSE</button>
										        
										        <button type="submit" class="btn btn-primary">EDIT</button>
										      </div>
									      </form>
								    </div>
								  </div>
								</div>
							
							
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	
	
</body>
</html>