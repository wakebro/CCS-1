<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>작성창</h1>
	<form action="/ccs/write_proc.do" method="post">
		<table border="1">
			<tr>
				<td>글 제목</td>
				<td><input type="text" name="b_title" size="40"></td>
			</tr>
			<tr>
				<td>본문</td>
				<td><textarea rows="20" cols="50" name="b_content"></textarea></td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td><input type="text" value="${userInfo.name }" readonly="readonly"></td>
				<td><input type="hidden" name="m_id" value="${userInfo.id}"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글쓰기">
					<input type="reset" value="초기화">
					<a href="/ccs/board.do"><input type="button" value="글목록"></a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>