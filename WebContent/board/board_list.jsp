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
	<h1>어서오세요, ${userInfo.name }님</h1>
	<a href="/ccs/logout.do">로그아웃</a>
	<table border="1">
		<tr>
			<td><a href="/ccs/main.do"><input type="submit" value="메인화면"></a></td>
			<td><a href="/ccs/userinfo.do"><input type="submit" value="내 정보"></a></td>
			<td><a href="/ccs/board.do?page=1"><input type="submit" value="게시판"></a></td>
			<c:set var="dept" value="${admin }"></c:set>
			<c:if test="${dept eq '관리자' }">
				<td><a href="/ccs/member.do?page=1"><input type="submit" value="직원목록"></a></td>
			</c:if>
		</tr>
	</table>
	<h1>게시판 창</h1>
	전체 글 : ${pageDTO.total }
	<table border="1">
		<tr>
			<td><a href="/ccs/write.do"><input type="button" value="글쓰기"></a>
		</tr>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>글쓴이</th>
			<th>쓴날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="list" items="${pageDTO.boardList }">
		<tr>
			<td>${list.b_no }</td>
			<td><a href="/ccs/boarddetail.do?b_no=${list.b_no}">${list.b_title}</a></td>
			<td>${list.m_id }</td>
			<td>${list.b_date }</td>
			<td>${list.b_view }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<%--페이징 버튼 만들기
				표현할 글이 있는 경우에만 버튼을 표시 --%>
					<c:if test="${pageDTO.hasBoard() }">
					
					<%-- 뒤로가기 버튼을 표시할지 말지 결정하는 부분 --%>
						<c:if test="${pageDTO.startPage > 10 }">
							<a href="/ccs/board.do?page=${pageDTO.startPage - 10}">
								<input type="button" value="이전">			
							</a>
						</c:if>
						
						<%-- 페이지 번호 10개 묶음을 깔아주는 부분 --%>
						<c:forEach var="pNo" begin="${pageDTO.startPage }" end="${pageDTO.endPage }">			
								<a href="board.do?page=${pNo }">${pNo }</a>
						</c:forEach>
							
						<%-- 다음으로 가기 버튼을 표시할지 말지 결정하는 부분 --%>
						<c:if test="${pageDTO.endPage != pageDTO.totalPages }">
							<a href="/ccs/board.do?page=${pageDTO.startPage + 10}">
								<input type="button" value="다음">
							</a>
						</c:if>
					</c:if>
				</td>
			</tr>
	</table>
</body>
</html>