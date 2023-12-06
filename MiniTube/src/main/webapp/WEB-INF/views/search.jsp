<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action=<c:url value="/search" />>
			<input type="text" name="v_title" placeholder="search" />
			<button type="submit">
				Search
			</button>
		</form>
	</div>
	<section>
		<c:forEach items="${videos}" var="videoEle">
			<a href=<c:url value="/video/watch/${videoEle.getV_id()}" />>
				<img src=<c:url value="/librarythumbs/${videoEle.getV_thumbnail()}" /> />
			</a>
		</c:forEach>
	</section>
</body>
</html>