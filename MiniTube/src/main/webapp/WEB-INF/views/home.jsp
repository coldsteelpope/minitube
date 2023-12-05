<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.google.minitube.member.MemberVo"%>

<%
	MemberVo loginedMemberVo = (MemberVo) session.getAttribute("loginedMemberVo");
%>

<html>
<head>
	<title>Home</title>
</head>
<body>
	
<h1>
	Hello world!  
</h1>
<% 
if(loginedMemberVo != null)
{	
%>
	<a href="<c:url value='/auth/signoutConfirm' />">Logout</a>
<%
}
else
{
%>
	<a href="/minitube/auth/signin">Login</a>
<%
} 
%>

<c:forEach items="${videos}" var="videoEle">
	<div>
		<form action="<c:url value="/video/delete/${videoEle.getV_id()}" />">
			<button type="submit">Delete</button>
		</form>
	</div>
	<a href=<c:url value="/video/watch/${videoEle.getV_id()}" />>
		<img src=<c:url value="/librarythumbs/${videoEle.getV_thumbnail()}" /> />
	</a>
</c:forEach>

</body>
</html>
