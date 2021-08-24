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
	<div class="header">
		<h1>로그인이 완료되었습니다.</h1>
		<div class="logo">
			<div id="logo_hello"><h1>${userInfo.m_Name }님, 환영합니다.</h1></div>
			<div id="logout"><a href="/ccs/logout.do">로그아웃</a></div>
		</div>
	</div>
	<div class="body">
		<div class="sider">
			<a href="/ccs/commute.do"><input type="submit" value="메인화면"></a>
			<a href="/ccs/board.do?page=1"><input type="submit" value="게시판"></a>
			<a href="/ccs/userinfo.do"><input type="submit" value="내 정보"></a>
			<a href="/ccs/approval.do"><input type="submit" value="결재창"></a>
			<c:set var="dept" value="${admin }"></c:set>
			<c:if test="${dept eq '관리자' }">
				<a href="/ccs/admin.do"><input type="submit" value="관리자창"></a>
			</c:if>
		</div>
	</div>
	<hr>
	<h2>회원정보 창</h2>
	<table border="1">
		<tr>
			<th>이름</th>
			<td>${userInfo.m_Name }</td>
		</tr>
		<tr>
			<th>사번</th>
			<td>${userInfo.m_No }</td>
		</tr>
		<tr>
			<th>부서</th>
			<td>${userInfo.dept }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${userInfo.m_Phone }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${userInfo.m_Email }</td>
		</tr>
		<tr>
			<td colspan="2" ><a href="/ccs/update.do"><input type="submit" value="회원정보 수정" style="width: 100%"></a>
		</tr>
		
	</table>
</body>
</html>