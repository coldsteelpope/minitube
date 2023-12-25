<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${like eq true}">
		<form action="<c:url value="/like/delete/video/${video.getV_id()}" />" method="POST">
			<input type="hidden" name="_method" value="DELETE"/>
			<button type="submit" class="btn btn-danger">좋아요 취소</button>
		</form>
	</c:when>
	<c:when test="${like eq false}">
		<c:choose>
		 	<c:when test="${empty member}">
				<a href="<c:url value="/auth/signin" />" class="btn btn-outline-danger">
					좋아요
				</a>
			</c:when>
			<c:otherwise>
				<form action="<c:url value="/like/video/${video.v_id}" />" method="post">
					<button type="submit" class="btn btn-outline-danger">좋아요 로그인</button>
				</form>
			</c:otherwise>
		</c:choose>
	</c:when>
</c:choose>