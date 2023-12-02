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
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
