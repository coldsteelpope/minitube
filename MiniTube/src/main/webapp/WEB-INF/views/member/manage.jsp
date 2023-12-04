<%@page import="com.google.minitube.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>Manage</title>
</head>
<body>
	<h1>Hi!</h1>
	<section>
		<div>
			<c:forEach items="${member.getM_videos()}" var="m_video">
				<img src="<c:url value="/librarythumbs/${m_video.getV_thumbnail()}" />" />
			</c:forEach>
		</div>
	</section>
</body>
</html>