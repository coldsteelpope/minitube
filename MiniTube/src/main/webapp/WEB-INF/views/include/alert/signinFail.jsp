<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${fail eq true}">
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
			<strong>Sign In Fail</strong> Please Try Again :)
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	</c:when>
	<c:otherwise></c:otherwise>
</c:choose>