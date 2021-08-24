<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" 
rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
 crossorigin="anonymous">
<meta charset="UTF-8">
<title>CCS - Company Commute System</title>
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
	<h2><a href="/ccs/board.do">CCS</a></h2>
	
	<a href="/ccs/board.do">최신순</a>&nbsp;
	
	<a href="/ccs/boardView.do">조회순</a>&nbsp;
	
	<form style='display:inline' action="/ccs/boardSearch.do" method="post">
		<input type="text" name="keyword" placeholder="검색어"/>
		<input class="btn btn-success" type="submit" value="검색"/> 
	</form><br></br>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>글쓴이</th>
				<th>글 쓴 날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList }">
			<tr>
				<td>${board.b_no }</td>
				<td><a href="/ccs/boardDetail.do?b_no=${board.b_no }">${board.b_title }</a></td>
				<td>${board.b_view }</td>
				<td>${board.m_id }</td>
				<td>${board.b_date }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<!--  게시판 메인, 최신순 페이징 버튼 -->
		<c:if test="${boardTotal > 0}">
			<c:if test="${pageDTO.startPage > 10 }">
				<button type="button" class="btn btn-light" onclick="location.href='/ccs/board.do?page=${pageDTO.startPage - 10}'">이전</button>
			</c:if>	
			<c:forEach var="pNo" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
				<button type="button" class="btn btn-light" onclick="location.href='/ccs/board.do?page=${pNo}'">${pNo }</button>
			</c:forEach>	
			<c:if test="${pageDTO.endPage < pageDTO.totalPages }">
				<button type="button" class="btn btn-light" onclick="location.href='/ccs/board.do?page=${pageDTO.startPage + 10}'">다음</button>
			</c:if>	
		</c:if>
		<!--  게시판 조회순 페이징 버튼 -->
		<c:if test="${viewTotal > 0}">
			<c:if test="${pageDTO.startPage > 10 }">
				<button type="button" class="btn btn-light" onclick="location.href='/ccs/boardView.do?page=${pageDTO.startPage - 10}'">이전</button>
			</c:if>	
			<c:forEach var="pNo" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
				<button type="button" class="btn btn-light" onclick="location.href='/ccs/boardView.do?page=${pNo}'">${pNo }</button>
			</c:forEach>	
			<c:if test="${pageDTO.endPage < pageDTO.totalPages }">
				<button type="button" class="btn btn-light" onclick="location.href='/ccs/boardView.do?page=${pageDTO.startPage + 10}'">다음</button>
			</c:if>	
		</c:if>
		<!-- 게시판 검색 페이징 버튼 -->
		<c:if test="${searchTotal > 0 }">
			<c:if test="${pageDTO.startPage > 10 }">
				<form style='display:inline' action="/ccs/boardSearch.do?page=${pageDTO.startPage - 10}">
					<input type="hidden" name="keyword" value="${keyword }"/>
					<input type="hidden" name="page" value="${pageDTO.startPage - 10}"/>
					<input class="btn btn-light" type="submit" value="이전"/> 
				</form>
			</c:if>	
			<c:forEach var="pNo" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
				<form style='display:inline' action="/ccs/boardSearch.do?page=${pNo}">
					<input type="hidden" name="keyword" value="${keyword }"/>
					<input type="hidden" name="page" value="${pNo}"/>
					<input class="btn btn-light" type="submit" value="${pNo}"/> 
				</form>
			</c:forEach>	
			<c:if test="${pageDTO.endPage < pageDTO.totalPages }">
				<form style='display:inline' action="/ccs/boardSearch.do?page=${pageDTO.startPage + 10}">
					<input type="hidden" name="keyword" value="${keyword }"/>
					<input type="hidden" name="page" value="${pageDTO.startPage + 10}"/>
					<input class="btn btn-light" type="submit" value="다음"/> 
				</form>
			</c:if>	
		</c:if>
	</p>
	<button type="button" onclick="location.href='http://localhost:8181/ccs/board/board_write.jsp'">새 글 쓰기</button>&nbsp;
	<button type="button" onclick="location.href='/ccs/logout.do'">로그아웃</button>&nbsp;
</body>
</html>