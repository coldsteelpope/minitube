<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form
	action="<c:url value="/video/delete/${param.v_id}" />"
	method="post">
	<button class="btn btn-lg btn-danger w-100" type="submit">Delete</button>
</form>