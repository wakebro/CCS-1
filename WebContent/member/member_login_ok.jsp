<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" 
	rel="stylesheet" 
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
 	crossorigin="anonymous">
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<div class="container">
	<div class="text-center">
		<h1>로그인이 완료되었습니다.</h1>
		<div class="logo">
			<div id="logo_hello"><h1>${userInfo.m_Name }님, 환영합니다.</h1></div>
			
		</div>
	</div>
		<div class="d-grid gap-2 col-6 mx-auto">
			<a href="/ccs/commute.do"><input type="submit" class="btn btn-outline-secondary" value="메인화면"></a>
			<a href="/ccs/board.do?page=1"><input type="submit" class="btn btn-outline-secondary" value="&ensp;게시판&ensp;"></a>
			<a href="/ccs/userinfo.do"><input type="submit" class="btn btn-outline-secondary" value="&ensp;내정보&ensp;"></a>
			<a href="/ccs/approval.do"><input type="submit" class="btn btn-outline-secondary" value="&ensp;결재창&ensp;"></a>
			<div id="logout"><button type="button" class="btn btn-outline-secondary" onclick="location.href='/ccs/logout.do'">로그아웃</button></div>
			<c:set var="dept" value="${admin }"></c:set>
			<c:if test="${dept eq '관리자' }">
				<a href="/ccs/admin.do"><input type="submit" class="btn btn-outline-secondary" value="관리자창"></a>
			</c:if>
		</div>
	</div>
	
	
	
</body>
</html>