<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<video
		autoplay="autoplay"
		controls="controls"
		class="w-100"
		src="<c:url value="/libraryVideos/${video.getV_video()}" />"
	>
	</video>
</div>