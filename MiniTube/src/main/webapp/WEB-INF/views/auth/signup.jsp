<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mini-tube</title>
</head>
<body>
	<div>
		<form action="/minitube/auth/signupConfirm" method="POST">
			<input type="email" placeholder="이메일" name="m_mail" />
			<input type="password" placeholder="비밀번호" name="m_pw" />
			<input type="text" placeholder="성" name="m_lastname" />
			<input type="text" placeholder="이름" name="m_firstname" />
			<input type="submit" value="로그인" />
		</form>
	</div>
</body>
</html>