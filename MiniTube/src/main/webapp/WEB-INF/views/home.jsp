<%@page import="com.google.minitube.dto.Member"%>
<%@page import="com.google.minitube.video.VideoVo"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
	Member loginedMemberVo = (Member) session.getAttribute("loginedMember");
	List<VideoVo> topVideoVos = (List<VideoVo>)request.getAttribute("topVideos");
	int topVideoVosNum = topVideoVos.size();
%>

<html>
<head lang="en">
	<title>Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
		
</head>
<body style="background-color: #0b0c0f;">
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" aria-label="Ninth navbar example">
	    <div class="container-xl">
	      <a class="navbar-brand" href="<c:url value="/" />">Container XL</a>
	      <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07XL" aria-controls="navbarsExample07XL" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	
	      <div class="navbar-collapse collapse" id="navbarsExample07XL" style="">
	      
	      	<%
	      	if(loginedMemberVo != null)
	      	{
	      	%>
	      	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
	          <li class="nav-item">
	            <a class="nav-link active" aria-current="page" href="<c:url value="/member/profile" />/<%=loginedMemberVo.getM_id()%>">PROFILE</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="<c:url value="/member/uploadVideo" />">UPLOAD</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="<c:url value="/auth/signoutConfirm" />">LOGOUT</a>
	          </li>
	        </ul>
	      	<%
	      	}
	      	else
	      	{
	      	%>
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
	          <li class="nav-item">
	            <a class="nav-link" href="<c:url value="/auth/signin" />">LOGIN</a>
	          </li>
	        </ul>
	        <%
	      	}
	        %>
	        <form role="search" action="<c:url value="/search" />">
	        	<input class="form-control mt-3" type="search" name="v_title" placeholder="search" aria-label="Search" />
	        </form>
	      </div>
	    </div>
  	</nav>


	<section class="mt-4">
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


    <!-- Optional JavaScript -->
	
</body>
</html>
