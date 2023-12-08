<%@page import="com.google.minitube.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>

<%
	MemberVo loginedMemberVo = (MemberVo)session.getAttribute("loginedMemberVo");
	MemberVo currentMemberVo = (MemberVo)request.getAttribute("member");
%>


<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
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
	            <%
				if(loginedMemberVo != null && loginedMemberVo.getM_id() == currentMemberVo.getM_id())
				{
				%>
	            <div class="mt-2">
		            <button type="button" class="btn btn-success btn-rounded" onclick="location.href='<c:url value="/member/uploadVideo" />'">
		              	UPLOAD VIDEO
		            </button>
					<button type="button" class="btn btn-outline-success btn-rounded" onclick="location.href='<c:url value="/member/manage/${member.getM_id()}" />'">
						MANGE PROFILE
					</button>
				</div>			
				<%
				}
				%>

	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
		
	<section class="mt-4 mb-5">
		<div class="container mt-3">
			<div>
				<h4 style="color:white;"><span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>${member.getM_firstname()} ${member.getM_lastname()}'S VIDEOS</strong></h4>
			</div>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-3">
				<c:forEach items="${member.getM_videos()}" var="videoEle">
					<div class="col">
						<a class="card shadow-sm" href=<c:url value="/video/watch/${videoEle.getV_id()}" />>
							<img class="w-100" style="height: 380px;" src=<c:url value="/librarythumbs/${videoEle.getV_thumbnail()}" /> />
						</a>
						<div class="mt-3">
							<h4 style="color: white"><strong>${videoEle.getV_title()}</strong></h4>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

</body>
</html>