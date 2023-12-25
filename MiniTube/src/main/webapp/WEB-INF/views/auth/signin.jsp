<%@page import="com.google.minitube.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	Member loginedMemberVo = (Member)session.getAttribute("loginedMember");
	if(loginedMemberVo != null)
	{
		response.sendRedirect("/minitube");
	}
	
	System.out.println(request.getAttribute("fail"));
 %>
 
 <% //boolean signUpFail = (boolean)request.getAttribute("fail"); %>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
<title>Sign In</title>
</head>
<body style="background-color: #0b0c0f;">

<jsp:include page="../include/navBar.jsp"></jsp:include>
	<section class="mt-4">
	  <!-- Jumbotron -->
		<div class="px-4 py-5 px-md-5 text-center text-lg-start">
	    	<div class="container">
	      		<div class="row gx-lg-5 align-items-center">
	        		<div class="col-lg-6 mb-5 mb-lg-0">
	          			<h1 class="my-5 display-3 fw-bold ls-tight" style="color: #1ce783;">
	            		The best Video Application <br />
	            		<span style="color: white;">for you</span>
	          			</h1>
	          			<p style="color: hsl(217, 10%, 50.8%)">
	            		Lorem ipsum dolor sit amet consectetur adipisicing elit.
	            		Eveniet, itaque accusantium odio, soluta, corrupti aliquam
	            		quibusdam tempora at cupiditate quis eum maiores libero
	            		veritatis? Dicta facilis sint aliquid ipsum atque?
	          			</p>
	        		</div>
	
	        		<div class="col-lg-6 mb-5 mb-lg-0">
	          			<div class="card">
	            			<div class="card-body py-5 px-md-5">
								<jsp:include page="../include/alert/signinFail.jsp" ></jsp:include>
		              			<form action="<c:url value="/auth/signinConfirm" />" method="post">
			                		<!-- Email input -->
			                		<div class="form-outline mb-4">
			                  			<input name="m_mail" type="email" id="form3Example3" class="form-control" />
			                  			<label class="form-label" for="form3Example3">Email address</label>
			                		</div>
			
			                		<!-- Password input -->
			                		<div class="form-outline mb-4">
			                  			<input name="m_pw" type="password" id="form3Example4" class="form-control" />
			                  			<label class="form-label" for="form3Example4">Password</label>
			                		</div>
			
			                		<!-- Submit button -->
			                		<button type="submit" class="btn btn-lg btn-block mb-4 w-100" style="background: #1ce783; color: white;">
			                  			<strong>Sign In</strong>
			                		</button>
			
			                		<!-- Register buttons -->
			                		<div class="text-center">
										Create Account <a href="<c:url value="/auth/signup" />">Here</a>
			                		</div>
		              			</form>
	            			</div>
	          			</div>
	        		</div>
	      		</div>
	    	</div>
	  	</div>
	</section>
</body>
</html>