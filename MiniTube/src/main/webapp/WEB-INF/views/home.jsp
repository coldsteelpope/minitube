<%@page import="com.google.minitube.video.VideoVo"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.google.minitube.member.MemberVo"%>

<%
	MemberVo loginedMemberVo = (MemberVo) session.getAttribute("loginedMemberVo");
	List<VideoVo> topVideoVos = (List<VideoVo>)request.getAttribute("topVideos");
	int topVideoVosNum = topVideoVos.size();
%>

<html>
<head>
	<title>Home</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body style="background-color: #0b0c0f;">
	
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
	<section>
		<div class="container">
			<div id="carouselExampleIndicators" class="carousel slide">
			  <div class="carousel-indicators">
			  <% 
			  for(int i = 0; i < topVideoVos.size(); ++i)
			  {
			  %>
			  	<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="<%=i%>" class="<%=(i == 0) ? "active" : ""%>" aria-current="<%=(i==0) ? "true" : "false" %>" aria-label="Slide <%=i+1%>" ></button>
			  <% 
			  }
			  %>  
			  </div>
			  <div class="carousel-inner">
			  	<% 
			  	for(int i = 0; i < topVideoVos.size(); ++i)
			  	{
			  	%>
			  	<div class="carousel-item <%=(i == 0) ? "active" : "" %>">
			  		<img style="height: 27.5em;" src="<c:url value="/librarythumbs/" />/<%=topVideoVos.get(i).getV_thumbnail()%>" class="d-block w-100" alt="alt"/>
			  		<div class="carousel-caption text-start">
			            <h1><%= topVideoVos.get(i).getV_title()%></h1>
			            <p class="opacity-75"><%=topVideoVos.get(i).getV_description()%></p>
			            <button class="btn btn-lg" style="background: #1ce783; color: white;" onclick="location.href='<c:url value="/video/watch" />/<%=topVideoVos.get(i).getV_id()%>'">
			           		WATCH NOW
			           	</button>
			        </div>
			  	</div>
			  	<%
			  	}
			  	%>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
		</div>
	</section>
	

	<section class="mt-4 mb-5">
		<div class="container mt-3">
			<div>
				<h4 style="color:white;"><span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>NEW VIDEOS</strong></h4>
			</div>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-3">
				<c:forEach items="${videos}" var="videoEle">
					<div class="col">
						<a class="card shadow-sm" href=<c:url value="/video/watch/${videoEle.getV_id()}" />>
							<img class="w-100" style="height: 380px;" src=<c:url value="/librarythumbs/${videoEle.getV_thumbnail()}" /> />
						</a>
						<div class="mt-3">
							<h4 style="color: white"><strong>${videoEle.getV_title()}</strong></h4>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
