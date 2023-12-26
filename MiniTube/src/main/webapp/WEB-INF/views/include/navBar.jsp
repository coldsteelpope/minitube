<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark" aria-label="Ninth navbar example">
	<div class="container-xl">
		<a class="navbar-brand" href="<c:url value="/" />">MINITUBE</a>
		<button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07XL" aria-controls="navbarsExample07XL" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="navbar-collapse collapse" id="navbarsExample07XL" style="">
			<c:choose>
				<c:when test="${empty member}">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item">
							<a class="nav-link" href="<c:url value="/auth/signin" />">LOGIN</a>
						</li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="<c:url value="/member/profile/${member.getM_id()}" />">PROFILE</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="<c:url value="/member/upload/video" />">UPLOAD</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="<c:url value="/auth/signoutConfirm" />">LOGOUT</a>
						</li>
					</ul>
				</c:otherwise>
			</c:choose>
			<form role="search" action="<c:url value="/search" />">
				<input class="form-control mt-3" type="search" name="v_title" placeholder="search" aria-label="Search" />
			</form>
		</div>
	</div>
</nav>