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
	<br><hr><br>
	<a href="/ccs/member.do?page=1">
		<input type="button" value="직원 목록">
	</a>
	<a href="/ccs/adminapproval.do">
		<input type="button" value="결제 서류">
	</a>
	<h2>결재 내용</h2>
	<table border="1">
		<tr>
			<th style="width: 90px">No.</th>
			<th style="width: 90px">종류</th>
			<th style="width: 90px">소속</th>
			<th style="width: 90px">사번</th>
			<th style="width: 130px">이름</th>
			<th style="width: 130px">메모</th>
			<th style="width: 130px">발생일</th>
			<th style="width: 150px">만료일</th>
			<th style="width: 90px">결제자</th>
			<th style="width: 90px">상태</th>
		</tr>
		<c:forEach var="list" items="${approveDTO.approveList }">
			<tr>
				<td>${list.a_no }</td>
				<td>${list.a_category }</td>
				<td>${list.d_name}</td>
				<td>${list.m_no}</td>
				<td>${list.m_name}</td>
				<td>${list.a_reason }</td>
				<td>${list.a_start_ }</td>
				<td>${list.a_end_ }</td>
				<td>${list.a_head }</td>
				<td>${list.a_status }</td>
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