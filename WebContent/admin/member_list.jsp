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
	<h1>어서오세요, ${userInfo.m_Name }님</h1>
	<a href="/ccs/logout.do">로그아웃</a>
	<table border="1">
		<tr>
			<td><a href="/ccs/main.do"><input type="submit" value="메인화면"></a></td>
			<td><a href="/ccs/board.do?page=1"><input type="submit" value="게시판"></a></td>
			<td><a href="/ccs/userinfo.do"><input type="submit" value="내 정보"></a></td>
			<td><a href="/ccs/approval.do"><input type="submit" value="결재창"></a></td>
			<c:set var="dept" value="${admin }"></c:set>
			<c:if test="${dept eq '관리자' }">
				<td><a href="/ccs/admin.do"><input type="submit" value="관리자창"></a></td>
			</c:if>
		</tr>
	</table>
	<br><hr><br>
	<a href="/ccs/member.do?page=1">
		<input type="button" value="직원 목록">
	</a>
	<a href="/ccs/adminapproval.do?page=1">
		<input type="button" value="결제 서류">
	</a>
	<h1>사원 리스트</h1>
	전체 사원수 : ${userPage.total }
	<table border="1">
		<tr>
			<th>이름</th>
			<th>사번</th>
			<th>아이디</th>
			<th>소속</th>
			<th>전화번호</th>
			<th>이메일</th>
		</tr>
		<c:forEach var="user" items="${userPage.userList }">
		<tr>
			<td>${user.name }</td>
			<td>${user.no}</td>
			<td>${user.id }</td>
			<td>${user.dept }</td>
			<td>${user.phone}</td>
			<td>${user.email}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="center">
					<c:if test="${userPage.hasBoard() }">
					
						<c:if test="${userPage.startPage > 10 }">
							<a href="/ccs/member.do?page=${userPage.startPage - 10}">
								<input type="button" value="이전">			
							</a>
						</c:if>
						
						<c:forEach var="pNo" begin="${userPage.startPage }" end="${userPage.endPage }">			
								<a href="member.do?page=${pNo }">${pNo }</a>
						</c:forEach>
							
						<c:if test="${userPage.endPage != userPage.totalPage }">
							<a href="/ccs/member.do?page=${userPage.startPage + 10}">
								<input type="button" value="다음">
							</a>
						</c:if>
					</c:if>
				</td>
			</tr>
	</table>
</body>
</html>