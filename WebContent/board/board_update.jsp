<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCS - ${board.b_title }</title>
</head>
<body>
	<form action="/ccs/boardUpdateOK.do" method="post">
		<table border="1">
			<tr>	
				<td>${board.b_no }</td>
				<th>글 번호</th>
			</tr>
			<tr>	
				<td>${board.b_view }</td>
				<th>조회수</th>
			</tr>
			<tr>	
				<td>${board.m_id }</td>
				<th>글쓴이</th>
			</tr>
			<tr>	
				<td>${board.b_date }</td>
			</tr>
			<tr>	
				<td><input type="text" name="title" value="${board.b_title }"/></td>
			</tr>
			<tr>	
				<td><textarea cols="50" rows="20" name="content">${board.b_content }</textarea></td>
			</tr>
		</table>
		<p>
			<button type="button" onclick="location.href='/ccs/boardDetail.do?b_no=${board.b_no }'">취소</button>&nbsp;
			<input type="reset" value="초기화">&nbsp;
			<input type="submit" value="저장">&nbsp;
		</p>
		<!-- hidden 태그를 이용해 나머지 요소들도 다 첨부 -->
		<input type="hidden" name="textNum" value="${board.b_no}" />
		<input type="hidden" name="view" value="${board.b_view}" />
		<input type="hidden" name="date" value="${board.b_date}" />
		<input type="hidden" name="writer" value="${board.m_id}" />
	</form>
</body>
</html>