<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
	<form action="/ccs/login_proc.do" method="post">
		<table border="1">
			<tr>
				<td><input size="15" type="text" name="login_id" placeholder="ID"></td>
				<td rowspan="2"><input type="submit" value="로그인" style="height: 50px"></td>
			</tr>
			<tr>
				<td><input size="16" type="password" name="login_pw" placeholder="PW"></td>
			</tr>
		</table>
	</form>
	<a href="/ccs/join.do">회원가입</a>
</body>
</html>