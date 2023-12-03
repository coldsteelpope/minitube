<%@page import="java.util.ArrayList"%>
<%@page import="com.google.minitube.comment.CommentVo"%>
<%@page import="java.util.List"%>
<%@page import="com.google.minitube.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	MemberVo loginedMemberVo = (MemberVo)session.getAttribute("loginedMemberVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<img src="<c:url value="/librarythumbs/${requestScope.video.v_thumbnail}"/>" />
	<video autoplay="autoplay" controls="controls" src="<c:url value="/libraryVideos/${requestScope.video.v_video}"/>"></video>
	
	
	<% 
	if(loginedMemberVo != null)
	{
	%>
	<div>
		<form action="<c:url value="/comment/confirm/${requestScope.video.v_id}" />" method="post">
			<textarea name="c_content" placeholder="댓글 추가..."></textarea>
			<button type="submit">
				댓글 추가
			</button>
		</form>
	</div>
	<% 
	}
	%>
	<div>
        <c:forEach items="${comments}" var="comment">
            <li>
            	${comment.getC_content()}
				<c:forEach items="${comment.getComments()}" var="childComment">
					${childComment.getC_content()}
				</c:forEach>
            </li>
            <!-- CommentVo의 content 등 각각의 필드에 맞게 출력 -->
        </c:forEach>
   	</div>
</body>
</html>