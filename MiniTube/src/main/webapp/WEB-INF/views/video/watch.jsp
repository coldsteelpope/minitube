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
<body style="background-color: #0b0c0f;">

	<section>
		<div class="container">
			<video 
				autoplay="autoplay" 
				controls="controls"
				class="w-100" 
				src="<c:url value="/libraryVideos/${requestScope.video.v_video}"/>">
			</video>
		</div>
	</section>
	<section class="mt-3">
		<div class="container">
			<h4 style="color:white;"><span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>VIDEO INFO</strong></h4>
		</div>
		<div class="container mt-3">
			<div class="row">
				<div class="col-sm-12 col-md-6 col-lg-4">
					<img 
						src="<c:url value="/librarythumbs/${video.getV_thumbnail()}" />" 
						style="width: 100%; height: 380px;"	
					/>
				</div>
				<div class="col-sm-12 col-md-6 col-lg-8 mt-3">
					<div>
						<div style="color: white;"><strong style="border-bottom: 2.5px solid #1ce783;">TITLE</strong></div>
						<h3 style="color: white;">${video.getV_title()}</h3>
					</div>
					<div class="mt-3">
						<div style="color: white;"><strong style="border-bottom: 2.5px solid #1ce783;">VIDEO MAKER</strong></div>
						<div class="mt-3">
							<a href=<c:url value="/member/profile/${video.getV_member().getM_id()}" />>
								<img 
									style="border-radius: 100%; width: 75px; height: 75px;"
									src="<c:url value="/libraryProfiles/${video.getV_member().getM_profile_img()}" />" 	
								/>
							</a>
						</div>
					</div>
					<div class="mt-3">
						<div style="color: white;"><strong style="border-bottom: 2.5px solid #1ce783;">DESCRIPTION</strong></div>
						<div class="mt-2">
							<p style="color: white;">
								${video.getV_description()}
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	
	<section class="mt-5 container">
		<div>
			<h4 style="color:white;"><span class="me-2" style="border-left: 5px solid #1ce783;"></span><strong>COMMENTS</strong></h4>
		</div>
		
		<% 
		if(loginedMemberVo != null)
		{
		%>
		<div class="mt-3">
			<form action="<c:url value="/comment/confirm/${requestScope.video.v_id}" />" method="post">
				<div class="form-floating">
				  <textarea name="c_content" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
				  <label for="floatingTextarea2">Comments</label>
				</div>
				
				<div class="d-flex justify-content-between align-items-center">
				    <div class="d-flex align-items-center">
	                </div>
	   				<div class="mt-3">
	              		<button class="btn btn-lg" style="background: #1ce783; color: white;" type="submit">
	              			<strong>
								COMMENT
							</strong>
						</button>
					</div>
                </div>
			</form>
		</div>
		<% 
		}
		%>
		
	<!-- Comment -->
	<section>
	  <div class="container my-5 py-2 text-dark">
	    <div class="row d-flex justify-content-center">
	      <div class="col">
	      
	      
	      	<%
	      	List<CommentVo> comments = (List<CommentVo>)request.getAttribute("comments");
	      	%>
	      	<%
	      	for(int i = 0; i < comments.size(); ++i)
	      	{
	      	CommentVo currentComment = comments.get(i);
	      	%>
	       	<div class="d-flex flex-start mb-4">
	      		<img 
	      			class="rounded-circle shadow-1-strong me-3"
	      			src="<c:url value="/libraryProfiles" />/<%=currentComment.getC_memberVo().getM_profile_img()%>"
	      			alt="name"
	      			width="65"
	      			height="65"
	      		/>
	      		<div class="card w-100">
	      			<div class="card-body p-4">
	      				<div class="">
	      					<h5><%=currentComment.getC_memberVo().getM_firstname() %> <%=currentComment.getC_memberVo().getM_lastname()%></h5>
	      					<p>
	      					<%=currentComment.getC_content()%>
	      					</p>
	      					<div class="d-flex justify-content-between align-items-center">
			                  <div class="d-flex align-items-center">
			                    <a href="#!" class="link-muted me-2"><i class="fas fa-thumbs-up me-1"></i>132</a>
			                    <a href="#!" class="link-muted"><i class="fas fa-thumbs-down me-1"></i>15</a>
			                  </div>
			                  <a href="#!" class="link-muted"><i class="fas fa-reply me-1"></i> Reply</a>
			                </div>
			                
			                
			                <!--  Child Comments -->
			                <%
			                if(loginedMemberVo != null)
			                {
			                %>
			                <div class="mt-3">
			                	<form action="<c:url value="/comment/childconfirm" />/<%=currentComment.getC_id()%>/${video.getV_id()}" method="post">
									<div class="form-floating">
									  <textarea name="c_content" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
									  <label for="floatingTextarea2">Comments</label>
									</div>
									<div class="d-flex justify-content-between align-items-center">
									    <div class="d-flex align-items-center">
						                </div>
						   				<div class="mt-3">
						              		<button class="btn btn-sm" style="background: #1ce783; color: white;" type="submit">
						              			<strong>
													REPLY
												</strong>
											</button>
										</div>
					                </div>
								</form>
			                </div>
			                <%
			                }
			                %>
			                <div>
			                <%
			                for(int childCommentIdx = 0; childCommentIdx < currentComment.getComments().size(); ++childCommentIdx)
			                {
			        	      	CommentVo childCurrentComment = currentComment.getComments().get(childCommentIdx);
			                %>
			                
			               	    <div class="d-flex flex-start mt-4">
			                      <a class="me-3" href="#">
			                        <img class="rounded-circle shadow-1-strong"
			                          src="<c:url value="/libraryProfiles" />/<%=childCurrentComment.getC_memberVo().getM_profile_img()%>" alt="avatar"
			                          width="65" height="65" />
			                      </a>
			                      <div class="flex-grow-1 flex-shrink-1">
			                        <div>
			                          <div class="d-flex justify-content-between align-items-center">
			                            <p class="mb-1">
			                              <%=childCurrentComment.getC_memberVo().getM_firstname()%> <%=childCurrentComment.getC_memberVo().getM_lastname()%>
			                            </p>
			                          </div>
			                          <p class="small mb-0">
			                          	<%=childCurrentComment.getC_content() %>
			                          </p>
			                        </div>
			                      </div>
			                    </div>
			                <%
			                }
			                %>
			                </div>
			                
	      				</div>
	      			</div>
	      		</div>
	      	</div>
	      	<%
	      	} 
	      	%>
	      </div>
	    </div>
	  </div>
	</section>	
		
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
</body>
</html>