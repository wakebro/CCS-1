<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="/ccs/join_proc.do" method="post">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="join_name" required="required"></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="join_id" required="required"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="join_pw" required="required"></td>
			</tr>
			<tr>
				<th>부서</th>
				<td>
					<select name="join_dept" style="width: 100%">
					<c:forEach var="dept" items="${deptlist}">
						<option value=${dept.dept_no }>${dept.dept_name}</option>
					</c:forEach></select>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="join_phone" required="required"></td>
			</tr>
			<tr>
				<th>email</th>
				<td><input type="text" name="join_email" required="required"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="가입" style="width: 100%" ></td>
			</tr>
		</table>
	</form>
</body>
</html>