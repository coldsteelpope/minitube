<%@page import="com.google.minitube.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberVo loginedMemberVo = (MemberVo)session.getAttribute("loginedMemberVo");
	if(loginedMemberVo == null)
	{
		response.sendRedirect("/minitube/auth/signin");
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Video</title>
</head>
<body>
	
	<form action="/minitube/video/uploadVideoConfirm" method="POST" enctype="multipart/form-data">
		<input type="text" name="v_title" placeholder="제목" />
		<textarea name="v_description"></textarea>
		<input type="file" name="thumbnailFile" />
		<input type="file" name="videoFile" />
		<button type="submit">
			Upload Video
		</button>
		<input type="reset" value="reset" />
	</form>
</body>
</html>