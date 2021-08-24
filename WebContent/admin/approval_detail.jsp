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
	<h2>결재 내용</h2>
	<form action="/ccs/adminapproconfirm.do" method="post">
		<table border="1">
			<tr>
				<th>No.</th>
				<td style="width: 30px">
					<input type="text" name="a_no" value="${approDetail.a_no }" readonly="readonly" style="width: 50px">
				</td>
				<th>종류</th>
				<td>${approDetail.a_category }</td>
				<th>소속</th>
				<td>${approDetail.d_name }</td>
			</tr>
			<tr>
				<th>사번</th>
				<td>${approDetail.m_no }</td>
				<th>이름</th>
				<td>${approDetail.m_name }</td>
				<th>결제자</th>
				<td>${approDetail.a_head }</td>
			</tr>
			<tr>
				<th>발생일</th>
				<td colspan="2">${approDetail.a_start_ }</td>
				<th>만료일</th>
				<td colspan="2">${approDetail.a_end_ }</td>
			</tr>
			<tr>
				<th>상태</th>
				<td colspan="5" style="text-align: center;">
					<c:choose>
					<c:when test="${approDetail.a_status.equals(\"대기\") }">
						<input type="radio" name="a_status" checked="checked" value="대기">대기
						<input type="radio" name="a_status" value="승인">승인
						<input type="radio" name="a_status" value="비승인">비승인
					</c:when>
					<c:when test="${approDetail.a_status.equals(\"승인\") }">
						<input type="radio" name="a_status"  value="대기">대기
						<input type="radio" name="a_status" checked="checked" value="승인">승인
						<input type="radio" name="a_status" value="비승인">비승인
					</c:when>
					<c:when test="${approDetail.a_status.equals(\"비승인\") }">
						<input type="radio" name="a_status" value="대기">대기
						<input type="radio" name="a_status" value="승인">승인
						<input type="radio" name="a_status" checked="checked"value="비승인">비승인
					</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>메모</th>
				<td colspan="5">
					<textarea name="a_reason" rows="30px" cols="50px">
						${approDetail.a_reason }
					</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="6" style="text-align: center">
					<input type="submit" value="결제">
				</td>
			<tr>
		</table>
	</form>
	
</body>
</html>