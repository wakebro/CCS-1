<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>어서오세요, ${userInfo.name }님</h1>
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
	<br>
	<hr>
<h2>글 수정</h2>
	<form action="boardupdate.do" method="post">
	<table border="1">
		<tr>
			<td>글번호</td><td>${content.b_no }</td>
		</tr>
		<tr>		
			<td>글쓴이</td><td>${content.m_id}</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td><input type="text" name="b_title" required="required" value= "${content.b_title}"></td>
		</tr>
			<td>글내용</td>
			<td><textarea cols="50" rows="10" name="b_content" >${content.b_content}</textarea></td>
		<tr>
			<td>작성날짜</td><td>${content.b_date}</td>
		</tr>
		<tr>
			<td>조회</td><td>${content.b_view}</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="/ccs/board.do"><input type="button" value="글목록"></a>			
					<input type="hidden" name="b_no" value="${content.b_no }" >
					<input type="hidden" name="m_id" value="${content.m_id}">
					<input type="hidden" name="b_date" value="${content.b_date}">
					<input type="hidden" name="b_view" value="${content.b_view}">	
					<input type="submit" value="글수정 확인">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>