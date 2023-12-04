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
			<img src=<c:url value="${member_img}" /> />
			
			<form 
				action="<c:url value="/member/edit/profile-image/${member.getM_id()}" />"
				method="post" 
				enctype="multipart/form-data"
			>	
				<input type="file" name="profileImageFile" />
				<button type="submit">
					이미지 변경하기
				</button>
			</form>
		</div>
	</section>
	
	<section>
		<div>
			<c:forEach items="${member.getM_videos()}" var="m_video">
				<img src="<c:url value="/librarythumbs/${m_video.getV_thumbnail()}" />" />
			</c:forEach>
		</div>
	</section>
</body>
</html>