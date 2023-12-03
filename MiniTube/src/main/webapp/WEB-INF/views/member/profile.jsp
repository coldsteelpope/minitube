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
<title>Insert title here</title>
</head>
<body>
	<div>
		${member.getM_lastname()} ${member.getM_firstname()}
	</div>
	<section>
		<div>
			<c:forEach items="${member.getM_videos()}" var="m_video">
				<a href="<c:url value="/video/watch/${m_video.getV_id()}" />">
					<img src="<c:url value="/librarythumbs/${m_video.getV_thumbnail()}" />" />
				</a>
				
			</c:forEach>
		</div>
	</section>
</body>
</html>