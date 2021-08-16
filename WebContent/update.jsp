<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<h2>회원정보 수정</h2>
	<a href="/ccs/logout.do">로그아웃</a>
	<table border="1">
		<tr>
			<td><a href="/ccs/userinfo.do"><input type="submit" value="내 정보"></a></td>
			<td><a href="#"><input type="submit" value="출/퇴근"></a></td>
			<td><a href="#"><input type="submit" value="게시판"></a></td>
	</table>
	<br>
	<form action="/ccs/update_proc.do" method="post">
	<table border="1">
		<tr>
			<th>이름</th>
			<td><input type="text" name="update_name" value= ${userInfo.name } readonly="readonly"></td>
		</tr>
		<tr>
			<th>사번</th>
			<td><input type="text" name="update_no" value= ${userInfo.no } readonly="readonly"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="update_id" value= ${userInfo.id } readonly="readonly"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="update_pw"></td>
		</tr>
		<tr>
			<th>부서</th>
			<td><input type="text" value="${userDept}" readonly="readonly"></td>
			<input type="hidden" name="update_dept" value=${userInfo.dept_no } readonly="readonly">
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="update_phone" value="${userInfo.phone }"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="update_email" value="${userInfo.email }"></td>
		</tr>
		<tr>
			<td colspan="2" ><input type="submit" value="확인" style="width: 100%">
		</tr>
	</table>
	</form>
</body>
</html>