<%@page import="com.google.minitube.video.VideoVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	List<VideoVo> videoVos = (List<VideoVo>)request.getAttribute("videos");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body style="background-color: #0b0c0f;">
	<div>
		<form action=<c:url value="/search" />>
			<input type="text" name="v_title" placeholder="search" />
			<button type="submit">
				Search
			</button>
		</form>
	</div>
	<section>
		<div class="container">
			<div>
				<h4 style="color:white;"><span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>Search Videos</strong></h4>
				<div>
					<p style="color: white;">We found a total of <strong><%=videoVos.size()%></strong> video(s).</p>
				</div>
			</div>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-3">
				<c:forEach items="${videos}" var="videoEle">
					<div class="col">
						<a 
							class="card shadow-sm"
							href=<c:url value="/video/watch/${videoEle.getV_id()}" />
						>
							<img 
								src=<c:url value="/librarythumbs/${videoEle.getV_thumbnail()}" /> 
								class="w-100"
								style="height: 380px;"
							/>
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