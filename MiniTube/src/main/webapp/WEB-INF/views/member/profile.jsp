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
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	

</head>
<body style="background-color: #0b0c0f;">
	
	<section class="vh-30">
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
</body>
</html>