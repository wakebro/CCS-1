<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
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
	<br>
	<table border="1">
		<tr>
			<th>이름</th>
			<td>${userInfo.name }</td>
		</tr>
		<tr>
			<th>사번</th>
			<td>${userInfo.no }</td>
		</tr>
		<tr>
			<th>부서</th>
			<td>${userDept }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${userInfo.phone }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${userInfo.email }</td>
		</tr>
		<tr>
			<td colspan="2" ><a href="/ccs/update.do"><input type="submit" value="회원정보 수정" style="width: 100%"></a>
		</tr>
		
	</table>
</body>
</html>