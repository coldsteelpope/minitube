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

<form action=<c:url value="/search" />>
	<input type="text" name="v_title" placeholder="search" />
	<button type="submit">
		Search
	</button>
</form>

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

	<a href=<c:url value="/video/watch/${videoEle.getV_id()}" />>
		<img src=<c:url value="/librarythumbs/${videoEle.getV_thumbnail()}" /> />
	</a>
</c:forEach>

</body>
</html>
