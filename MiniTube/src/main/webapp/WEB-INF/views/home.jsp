<%@page import="com.google.minitube.dto.Video"%>
<%@page import="com.google.minitube.dto.Member"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head lang="en">
	<title>Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body style="background-color: #0b0c0f;">
	<jsp:include page="./include/navBar.jsp"></jsp:include>
	
	<section class="mt-4">
		<jsp:include page="./include/video/topVideos.jsp"></jsp:include>
	</section>
	
	<section class="mt-4 mb-5 container">
		<jsp:include page="./include/video/videos.jsp"></jsp:include>
	</section>
	
</body>
</html>
