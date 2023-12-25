<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
				<div style="color: white;">
				<strong style="border-bottom: 2.5px solid #1ce783;">TITLE</strong>
				</div>
				<h3 style="color: white;">${video.getV_title()}</h3>
			</div>
			
			<div class="mt-3">
				<div style="color: white;">
					<strong style="border-bottom: 2.5px solid #1ce783;">VIDEO MAKER</strong>
				</div>
				<div class="mt-3">
					<a href=<c:url value="/member/profile/${videoMember.getM_id()}" />>
					
					<c:choose>
						<c:when test="${not empty videoMember.getM_profile_img()}">
							<img 
							style="border-radius: 100%; width: 75px; height: 75px;"
							src="<c:url value="/libraryProfiles/${videoMember.getM_profile_img()}" />" 	
							/>
						</c:when>
						<c:otherwise>
							<img 
							style="border-radius: 100%; width: 75px; height: 75px;"
							src="<c:url value="/resources/blank_pic.png" />" 	
							/>
						</c:otherwise>
					</c:choose>

					</a>
				</div>
			</div>
			<div class="mt-3">
				<div style="color: white;">
					<strong style="border-bottom: 2.5px solid #1ce783;">DESCRIPTION</strong>
				</div>
				<div class="mt-2">
					<p style="color: white;">
					${video.getV_description()}
					</p>
				</div>
			</div>
		</div>			
	</div>
</div>