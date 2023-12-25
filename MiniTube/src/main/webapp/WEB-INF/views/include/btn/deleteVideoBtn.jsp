<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form
	action="<c:url value="/video/delete/${video.getV_id()}/${member.getM_id()}" />"
	method="post">
	<button class="btn btn-lg btn-danger w-100" type="submit">Delete</button>
</form>