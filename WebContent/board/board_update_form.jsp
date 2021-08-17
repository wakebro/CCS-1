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
			<td>글번호</td><td>${content.bId }</td>
		</tr>
		<tr>		
			<td>글쓴이</td><td>${content.bName}</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td><input type="text" name="bTitle" required="required" value= "${content.bTitle}"></td>
		</tr>
			<td>글내용</td>
			<td><textarea cols="50" rows="10" name="bContent" >${content.bContent}</textarea></td>
		<tr>
			<td>작성날짜</td><td>${content.bDate}</td>
		</tr>
		<tr>
			<td>조회</td><td>${content.bHit}</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="/MyFirstWeb/boardselect.do"><input type="button" value="글목록"></a>			
					<input type="hidden" name="bId" value="${content.bId }" >
					<input type="hidden" name="bName" value="${content.bName}">
					<input type="hidden" name="bDate" value="${content.bDate}">
					<input type="hidden" name="bHit" value="${content.bHit}">	
					<input type="submit" value="글수정 확인">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>