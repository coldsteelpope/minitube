<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	
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

