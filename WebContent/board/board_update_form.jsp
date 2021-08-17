<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>수정창</h1>
	<a href=#>정보수정</a>
	<a href="logout.do">로그아웃</a>
	<form action="boardupdate.do" method="post">
	<table border="1">
		<tr>
			<td>글번호</td><td>${content.b_no }</td>
		</tr>
		<tr>		
			<td>글쓴이</td><td>${content.m_id}</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td><input type="text" name="b_title" required="required" value= "${content.b_title}"></td>
		</tr>
			<td>글내용</td>
			<td><textarea cols="50" rows="10" name="b_content" >${content.b_content}</textarea></td>
		<tr>
			<td>작성날짜</td><td>${content.b_date}</td>
		</tr>
		<tr>
			<td>조회</td><td>${content.b_view}</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="/ccs/board.do"><input type="button" value="글목록"></a>			
					<input type="hidden" name="b_no" value="${content.b_no }" >
					<input type="hidden" name="m_id" value="${content.m_id}">
					<input type="hidden" name="b_date" value="${content.b_date}">
					<input type="hidden" name="b_view" value="${content.b_view}">	
					<input type="submit" value="글수정 확인">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>