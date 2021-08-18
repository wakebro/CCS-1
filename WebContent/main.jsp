<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
</head>
<body>
	<h1>어서오세요, ${userInfo.name }님</h1>
	<a href="/ccs/logout.do">로그아웃</a>
	<table border="1">
		<tr>
			<td><a href="/ccs/main.do"><input type="submit" value="메인화면"></a></td>
			<td><a href="/ccs/userinfo.do"><input type="submit" value="내 정보"></a></td>
			<td><a href="/ccs/board.do?page=1"><input type="submit" value="게시판"></a></td>
			<c:set var="dept" value="${admin }"></c:set>
			<c:if test="${dept eq '관리자' }">
				<td><a href="/ccs/member.do?page=1"><input type="submit" value="직원목록"></a></td>
			</c:if>
		</tr>
	</table>
	<h1>Main 창</h1>
		<form action="/ccs/commute.do" method="post">
			<c:if test="${lastestDate.leave_work != null }">
				<input type="hidden" name="cKeyword" value="${userInfo.no}">
				<input type="submit" value="출근">
			</c:if>
			<c:if test="${lastestDate.leave_work == null }">
			<input type="hidden" name="cKeyword" value="${userInfo.no}">
			<input type="submit" value="퇴근">
			</c:if>
		</form>
		<hr>
		<table border="1">
			<tr>
				<td>No.</td>
				<td>사원번호</td>
				<td>이름</td>
				<td>출근시간</td>
				<td>퇴근시간</td>
			</tr>
			<c:forEach var="list" items="${commuteList}">
			<tr>
				<td>${list.c_no }</td>
				<td>${list.m_no }</td>
				<td>${userInfo.name}</td>
				<td>${list.attendance }</td>
				<td>${list.leave_work }</td>
			</tr>
			</c:forEach>
		</table>
</body>
</html>