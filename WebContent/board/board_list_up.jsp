<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCS - Company Commute System</title>
<style>
	*{font: 15px "굴림", Gulim;}
	header{
		padding-top: 20px;
	}
	#username{
		font-family: 'Black Han Sans', sans-serif;
		font-size: 300% ;
	}
	.body{
		height: 100vh;
		background-color: rgb(235, 232, 232);
	}
	.logo{
		width: 200px;
		padding-top: 20px;
		padding-right: 35px;
	}
	.userInfo{
		display: flex;
		flex-direction: column;
		justify-content: flex-end;
		margin-bottom: 10px;
	}
	.sider {
		height:200px;
	}
	.header-hr{
		margin-top: 0;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
</head>
<body>
	<div class="container">
		<header>
			<div class="row">
				<div class="col-md-2">
					<div class="logo row">
						<img src="../img/logo.png" onerror="this.src='../ccs/img/logo.png'">
					</div>
				</div>
				<div class="userInfo col-md-9">
					<div class="row">
						<div class="col-md-8">
							<span id="username">${userInfo.m_Name }</span>&nbsp;${userInfo.dept }/<a href="/ccs/logout.do">로그아웃</a>
						</div>
					</div>
				</div>
			</div>
		</header>
		<hr class="header-hr">
		<div class="body">
			<div class="row">
				<div class="sider btn-group-vertical col-md-2">
					<a href="/ccs/commute.do" class="btn btn-success" role="button">메인화면</a>
					<a href="/ccs/board.do?page=1" class="btn btn-success" role="button">게시판</a>
					<a href="/ccs/userinfo.do" class="btn btn-success" role="button">My Page</a>
					<a href="/ccs/approval.do" class="btn btn-success" role="button">결재창</a>
					<c:set var="dept" value="${admin }"></c:set>
					<c:if test="${dept eq '관리자' }">
						<a href="/ccs/admin.do" class="btn btn-success" role="button">관리자</a>
					</c:if>
				</div>
				<div class="col-md-10">
					<div class="row">
						<div class="col-md-4">
							<a href="/ccs/board.do" class="btn btn-outline-success">최신순</a>
							<a href="/ccs/boardView.do" class="btn btn-outline-success">조회순</a>
						</div>
						<div class="col-md-5 offset-md-3">
							<form style='display:inline' action="/ccs/boardSearch.do" method="post">
								<input type="text" name="keyword" placeholder="검색어" />
								<input class="btn btn-success" type="submit" value="검색" />
							</form>
						</div>
						<hr>
						<br>
						<br>
						<div>
							<div class="col-md-12">
								<button class="btn btn-success" onclick="location.href='/ccs/board/board_write.jsp'">글쓰기</button>
							</div>
						</div>
						<br>
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
								<ul class="pagination justify-content-center">
									<c:if test="${pageDTO.startPage > 10 }">
										<li class="page-item">
											<button type="button" class="page-link btn btn-light"
											onclick="location.href='/ccs/board.do?page=${pageDTO.startPage - 10}'"><<</button>
										</li>
									</c:if>
									<c:forEach var="pNo" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
										<li class="page-item">
											<button type="button" class="page-link btn btn-light"
											onclick="location.href='/ccs/board.do?page=${pNo}'">${pNo }</button>
										</li>
									</c:forEach>
									<c:if test="${pageDTO.endPage < pageDTO.totalPages }">
										<li class="page-item">
											<button type="button" class="page-link btn btn-light"
											onclick="location.href='/ccs/board.do?page=${pageDTO.startPage + 10}'">>></button>
										</li>
									</c:if>
								</ul>
							</c:if>
							<!--  게시판 조회순 페이징 버튼 -->
							<c:if test="${viewTotal > 0}">
								<ul class="pagination justify-content-center">
									<c:if test="${pageDTO.startPage > 10 }">
										<li class="page-item">
											<button type="button" class="page-link btn btn-light"
												onclick="location.href='/ccs/boardView.do?page=${pageDTO.startPage - 10}'"><<</button>
										</li>
									</c:if>
									<c:forEach var="pNo" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
										<li class="page-item">
											<button type="button" class="page-link btn btn-light"
												onclick="location.href='/ccs/boardView.do?page=${pNo}'">${pNo }</button>
										</li>
									</c:forEach>
									<c:if test="${pageDTO.endPage < pageDTO.totalPages }">
										<li class="page-item">
											<button type="button" class="page-link btn btn-light"
												onclick="location.href='/ccs/boardView.do?page=${pageDTO.startPage + 10}'">>></button>
										</li>
									</c:if>
								</ul>
							</c:if>
							<!-- 게시판 검색 페이징 버튼 -->
							<c:if test="${searchTotal > 0 }">
								<ul class="pagination justify-content-center">
									<c:if test="${pageDTO.startPage > 10 }">
										<li class="page-item">
											<button type="button" class="page-link btn btn-light"
												onclick="location.href='/ccs/boardSearch.do?page=${pageDTO.startPage - 10}&keyword=${keyword }'"><<</button>
										</li>
									</c:if>
									<c:forEach var="pNo" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
										<li class="page-item">
											<button type="button" class="page-link btn btn-light"
												onclick="location.href='/ccs/boardSearch.do?page=${pNo}&keyword=${keyword }'">${pNo}</button>
										</li>
									</c:forEach>
									<c:if test="${pageDTO.endPage < pageDTO.totalPages }">
										<li class="page-item">
											<button type="button" class="page-link btn btn-light"
												onclick="location.href='/ccs/boardSearch.do?page=${pageDTO.startPage + 10}&keyword=${keyword }'">>></button>
										</li>
									</c:if>
								</ul>
							</c:if>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>