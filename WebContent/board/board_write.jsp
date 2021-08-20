<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCS - Article 등록</title>
</head>
<body>
	<h2>새 글 쓰기</h2>
	<form action="/ccs/boardCreate.do" method="post">
		<table border="1">
			<tr>
				<td><input type="text" name="title" placeholder="제목을 입력해주세요" required="required"></td>
			</tr>
			<tr>
				<td><textarea cols="50" rows="20" name="content" required="required"></textarea></td>
			</tr>
			<tr>
				<td><input type="text" name="id" value="${session_id}" readonly></td>
			</tr>
		</table>
		<p>
			<button type="button" onclick="location.href='/ccs/board.do'">취소</button>&nbsp;
			<input type="submit" value="등록">&nbsp;
		</p>		
	</form>
</body>
</html>