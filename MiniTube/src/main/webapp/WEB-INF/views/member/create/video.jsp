<%@page import="com.google.minitube.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%
	MemberVo loginedMemberVo = (MemberVo)session.getAttribute("loginedMemberVo");
	if(loginedMemberVo == null)
	{
		response.sendRedirect("/minitube/auth/signin");
	}
	boolean isUploadFail = (boolean)request.getAttribute("fail");
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create Video</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
</head>
<body style="background-color: #0b0c0f;">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark" aria-label="Ninth navbar example">
	    <div class="container-xl">
	      <a class="navbar-brand" href="#">Container XL</a>
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
  	
  	<div class="row row-cols-1 row-cols-sm-2 g-0 mt-4 px-3">
  		<div class="col">
			<div>
				<h1 style="color: #1ce783;">
					<strong>Upload Your Video For Free :)</strong>
				</h1>
				<h2 class="text-white">Make Your Influence Easily</h2>
			</div>
		</div>
	
		<div class="col mt-3 px-3">
			<div>
				<%
				if(isUploadFail)
				{
				%>
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
				  <strong>Fail To Upload!</strong> Please Try Again :)
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
				<%
				}
				%>
				<form action="/minitube/video/uploadVideoConfirm" method="POST" enctype="multipart/form-data">
					<div>
						<label style="border-left: 5px solid #1ce783;" class="text-white" for="title_label"><strong class="ms-1">TITLE</strong></label>
						<input required id="title_label" type="text" name="v_title" placeholder="title" class="form-control mt-2" />
					</div>
					<div class="mt-2">
						<label style="border-left: 5px solid #1ce783;" class="text-white" for="description_label"><strong class="ms-1">DESCRIPTION</strong></label>
						<textarea required id="description_label" name="v_description" placeholder="description" class="form-control mt-2"></textarea>
					</div>
					<div class="mt-2">
						<label style="border-left: 5px solid #1ce783;" class="text-white" for="thumbnail_label"><strong class="ms-1">THUMBNAIL</strong></label>
						<br />
						<input required id="thumbnail_label" type="file" name="thumbnailFile" class="form-control mt-2"/>
					</div>
					<div class="mt-2">
						<label style="border-left: 5px solid #1ce783;" class="text-white" for="video_label"><strong class="ms-1">VIDEO</strong></label>
						<br />
						<input required id="video_label" type="file" name="videoFile" class="form-control mt-2" />
					</div>
					<div class="row row-cols-2 mt-4 g-0">
						<div class="col">
							<button type="submit" class="btn btn-lg text-white" style="background-color: #1ce783; width: 99%;">
								<strong>UPLOAD NOW</strong>
							</button>
						</div>
						<div class="col">
							<button type="reset" class="btn btn-lg btn-danger" style="width: 99%;">
								<strong>RESET</strong>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>