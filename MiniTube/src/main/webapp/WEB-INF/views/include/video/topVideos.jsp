<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<div id="carouselExampleIndicators" class="carousel slide">
		<div class="carousel-indicators">
			<c:forEach items="${topVideos}" var="topVideo" varStatus="status">
				<c:set var="isActive" value="${status.index == 0 ? 'active' : ''}" />
				<c:set var="ariaCurrent" value="${status.index == 0 ? 'true' : 'false'}" />
				<button
					type="button"
					data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="${status.index}"
					class="${isActive}"
					aria-current="${ariaCurrent}"
					aria-label="Slide ${status.count}"
				>
				</button>
			</c:forEach>
		</div>
		<div class="carousel-inner">
			<c:forEach items="${topVideos}" var="topVideo" varStatus="status">
				<c:set var="isActiveItem" value="${status.index == 0 ? 'active' : ''}" />
				<div class="carousel-item ${isActiveItem}">
					<img style="height: 27.5em;" src="<c:url value="/librarythumbs/${topVideo.getV_thumbnail()}" />" class="d-block w-100" alt="alt"/>
					<div class="carousel-caption text-start">
						<h1><c:out value="${topVideo.getV_title()}" /></h1>
						<p><c:out value="${topVideo.getV_description()}" /></p>
						<a class="btn btn-lg" style="background: #1ce783; color: white;" href="<c:url value="/video/watch/${topVideo.getV_id()}" />">
							WATCH NOW
						</a>
					</div>
				</div>
			</c:forEach>
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

