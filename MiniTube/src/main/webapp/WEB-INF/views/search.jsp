<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
</head>
<body style="background-color: #0b0c0f;">

	<jsp:include page="./include/navBar.jsp"></jsp:include>
  	
  	<section class="mt-4 container">
  		<div>
  			<h4 style="color:white;"><span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>Search Videos</strong></h4>
  			<div>
  				<p style="color: white;">We Found A Total Of <strong><c:out value="${videos.size()}"></c:out></strong> Video(s)</p>
  			</div>
  		</div>
  		<jsp:include page="./include/video/videos.jsp" ></jsp:include>
  	</section>
</body>
</html>