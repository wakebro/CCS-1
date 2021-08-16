<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<td><input type="text" name="title" placeholder="제목을 입력해주세요"></td>
			</tr>
			<tr>
				<td><textarea cols="50" rows="20" name="content"></textarea></td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<p>
			<button type="button" onclick="location.href='/ccs/boardSelect.do'">취소</button>&nbsp;
			<input type="submit" value="등록">&nbsp;
		</p>		
	</form>
</body>
</html>