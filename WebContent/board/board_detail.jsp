<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>상세창</h1>
	<a href=#>정보수정</a>
	<a href="logout.do">로그아웃</a>
	<table border="1">
		<tr>
			<td>글번호</td><td>${content.b_no }</td>
		</tr>
		<tr>		
			<td>글쓴이</td><td>${content.m_id}</td>
		</tr>
		<tr>
			<td>글제목</td><td>${content.b_title}</td>
		</tr>
			<td>글내용</td>
			<td><textarea cols="50" rows="10" readonly="readonly">${content.b_content}</textarea></td>
		<tr>
			<td>작성날짜</td><td>${content.b_date}</td>
		</tr>
		<tr>
			<td>조회</td><td>${content.b_view}</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="/ccs/board.do"><input type="button" value="글목록"></a>			
				<form action="/ccs/boardupdateform.do" method="post">
					<input type="hidden" name="b_no" value="${content.b_no }" >
					<input type="submit" value="글수정">
				</form>
				<form action="/ccs/boarddelete.do" method="post">
					<input type="hidden" name="b_no" value="${content.b_no }" >
					<input type="submit" value="글삭제">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>