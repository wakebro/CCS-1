<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
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
	<h2>회원정보 수정</h2>
	<br>
	<form action="/ccs/update_proc.do" method="post">
	<table border="1">
		<tr>
			<th>이름</th>
			<td><input type="text" name="update_name" value= ${userInfo.m_Name } readonly="readonly"></td>
		</tr>
		<tr>
			<th>사번</th>
			<td><input type="text" name="update_no" value= ${userInfo.m_No } readonly="readonly"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="update_id" value= ${userInfo.m_Id } readonly="readonly"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="update_pw"></td>
		</tr>
		<tr>
			<th>부서</th>
			<td><input type="hidden" name="update_dept" value="${userInfo.dept_no}">
				<input type="text" value="${userInfo.dept}" readonly="readonly">
			</td>
			
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="update_phone" value="${userInfo.m_Phone }"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="update_email" value="${userInfo.m_Email }"></td>
		</tr>
		<tr>
			<td colspan="2" ><input type="submit" value="확인" style="width: 100%">
		</tr>
	</table>
	</form>
</body>
</html>