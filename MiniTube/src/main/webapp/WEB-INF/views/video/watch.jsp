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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
	<div>
		<video autoplay="autoplay" controls="controls" src="<c:url value="/libraryVideos/${requestScope.video.v_video}"/>"></video>
		<div>
			<a href=<c:url value="/member/profile/${video.getV_member().getM_id()}" />>
				<img src="<c:url value="/libraryProfiles/${video.getV_member().getM_profile_img()}" />" />
			</a>
		</div>
	</div>
	
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
		<%
		List<CommentVo> comments = (List<CommentVo>)request.getAttribute("comments");
		for(int i = 0; i < comments.size(); ++i)
		{
			CommentVo currentComment = comments.get(i);
		%>
			<div>
				<%
				if(loginedMemberVo != null && (loginedMemberVo.getM_id() == currentComment.getC_m_id()))
				{
				%>
				<div>
					<form action="<c:url value="/comment/delete" />/<%=currentComment.getC_id() %>/${video.getV_id()}" method="post">
						<button type="submit">
							DELETE COMMENT
						</button>
					</form>
				</div>
				<%
				}
				%>
				<%= comments.get(i).getC_content() %>
				<% 
				if(loginedMemberVo != null)
				{
				%>
				<div>
					<form action="<c:url value="/comment/childconfirm" />/<%=currentComment.getC_id()%>/${video.getV_id()}" method="post">
						<textarea name="c_content"></textarea>
						<button type="submit">
							댓글
						</button>
					</form>
				</div>
				<% 
				}
				%>
			</div>
			<div>
				<% 
				for(int j = 0; j < currentComment.getComments().size(); ++j)
				{
					CommentVo currentChildComment = currentComment.getComments().get(j);
				%>
				<div>
					<div>
					<%
					if(loginedMemberVo != null && (loginedMemberVo.getM_id() == currentChildComment.getC_m_id()))
					{
					%>
					<div>
						<form action="<c:url value="/comment/child/delete" />/<%=currentChildComment.getC_id()%>/${video.getV_id()}" method="post">
							<button type="submit">
								DELETE Child COMMENT
							</button>
						</form>
					</div>
					<%
					}
					%>
					</div>
					<div>
						<%= currentChildComment.getC_content() %>
					</div>
				</div>
				<%
				}
				%>
			</div>
		<% 
		}
		%>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
</body>
</html>