<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h1>어서오세요, ${userInfo.name }님</h1>
	<a href="/ccs/logout.do">로그아웃</a>
	<table border="1">
		<tr>
			<td><a href="/ccs/main.do"><input type="submit" value="메인화면"></a></td>
			<td><a href="/ccs/userinfo.do"><input type="submit" value="내 정보"></a></td>
			<td><a href="#"><input type="submit" value="출/퇴근"></a></td>
			<td><a href="/ccs/board.do"><input type="submit" value="게시판"></a></td>
			<c:set var="dept" value="${admin }"></c:set>
			<c:if test="${dept eq '경영지원' }">
				<td><a href="#"><input type="submit" value="직원목록"></a></td>
			</c:if>
		</tr>
	</table>
	<h1>게시판 창</h1>
	<table border="1">
		<tr>
			<td><a href="/ccs/write.do"><input type="button" value="글쓰기"></a>
		</tr>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>글쓴이</th>
			<th>쓴날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="list" items="${boardList }">
		<tr>
			<td>${list.b_no }</td>
			<td><a href="/ccs/boarddetail.do?b_no=${list.b_no}">${list.b_title}</a></td>
			<td>${list.m_id }</td>
			<td>${list.b_date }</td>
			<td>${list.b_view }</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>