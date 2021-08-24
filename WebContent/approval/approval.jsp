<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결재창</title>
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
	<h2>결재창</h2>
	<form action="approval_proc.do" method="post">
		<table border="1">
			<tr>
				<th style="width: 90px">종류</th>
				<th style="width: 130px">발생일</th>
				<th style="width: 130px">만료일</th>
				<th style="width: 150px">메모</th>
			</tr>
			<tr>
				<td>
					<select name="a_category" style="width: 100%">
						<c:forEach var="list" items="${categoryList}">
							<option value="${list.cate_name }">${list.cate_name }</option>
						</c:forEach>
					</select>
				</td>
				<td><input type="date" name="a_start"></td>
				<td><input type="date" name="a_end"></td>
				<td><textarea name="a_reason" rows="" cols=""></textarea></td>
				<td><input type="submit" value="요청">
			</tr>
		</table>
	</form>
	<br>
	<hr>
	<h2>요청 기록</h2>
	<table border="1">
		<tr>
			<th style="width: 90px">상태</th>
			<th style="width: 90px">종류</th>
			<th style="width: 130px">발생일</th>
			<th style="width: 130px">만료일</th>
			<th style="width: 150px">메모</th>
			<th style="width: 90px">결제자</th>
		</tr>
		<c:forEach var="list" items="${approveDTO.approveList }">
			<tr>
				<td>${list.a_status }</td>
				<td>${list.a_category }</td>
				<td>${list.a_start_ }</td>
				<td>${list.a_end_ }</td>
				<td>${list.a_reason }</td>
				<td>${list.a_head }</td>
			</tr>
		</c:forEach>
			<tr>
				<td colspan="6" style="text-align: center">
					<c:if test="${approveDTO.hasList() }">
						<c:if test="${approveDTO.startPage > 10 }">
							<a href="approval.do?page=${approveDTO.startPage - 10 }">
							<input type="button" value="이전">
							</a>
						</c:if>
						
						<c:forEach var="pNo" begin="${approveDTO.startPage }" end="${approveDTO.endPage }">
							<a href="approval.do?page=${pNo }">${pNo}</a>
						</c:forEach>
						
						<c:if test="${approveDTO.endPage > approveDTO.totalPages }">
							<a href="approval.do?page=${approveDTO.startPage + 10 }">
							<input type="button" value="다음">
							</a>
						</c:if>
					</c:if>
				</td>
			</tr>
	</table>
</body>
</html>