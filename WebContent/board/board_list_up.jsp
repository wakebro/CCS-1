<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCS - Company Commute System</title>
</head>
<body>
	<h2>CCS</h2>
	<table border="1">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>조회수</th>
				<th>글쓴이</th>
				<th>글 쓴 날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList }">
			<tr>
				<td>${board.b_no }</td>
				<td><a href="/ccs/boardDetail.do?b_no=${board.b_no }">${board.b_title }</a></td>
				<td>${board.b_view }</td>
				<td>${board.m_id }</td>
				<td>${board.b_date }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<button type="button" onclick="location.href='http://localhost:8181/ccs/board/board_write.jsp'">새 글 쓰기</button>&nbsp;
		<button type="button" onclick="location.href='/ccs/logout.do'">로그아웃</button>&nbsp;
	</p>	
</body>
</html>