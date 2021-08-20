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
	<h2><a href="/ccs/board.do">CCS</a></h2>
	
	<a href="/ccs/boardView.do">조회순</a>&nbsp;
	
	<form style='display:inline' action="/ccs/boardSearch.do" method="post">
		<input type="text" name="keyword" placeholder="검색어"/>
		<input class="btn btn-search" type="submit" value="검색"/> 
	</form><br></br>
	
	<table border="1">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>제목</th>
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
		<c:if test="${pageDTO.hasBoard() }">
			<c:if test="${pageDTO.startPage > 10 }">
				<button type="button" onclick="location.href='/ccs/board.do?page=${pageDTO.startPage - 10}'">이전</button>&nbsp;
			</c:if>	
			<c:forEach var="pNo" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
				<button type="button" onclick="location.href='/ccs/board.do?page=${pNo}'">${pNo }</button>&nbsp;
			</c:forEach>	
			<c:if test="${pageDTO.endPage < pageDTO.totalPages }">
				<button type="button" onclick="location.href='/ccs/board.do?page=${pageDTO.startPage + 10}'">다음</button>&nbsp;
			</c:if>	
		</c:if>
	</p>
	<button type="button" onclick="location.href='http://localhost:8181/ccs/board/board_write.jsp'">새 글 쓰기</button>&nbsp;
	<button type="button" onclick="location.href='/ccs/logout.do'">로그아웃</button>&nbsp;
</body>
</html>