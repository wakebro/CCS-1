<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
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
						<div class="col-md-12">
							<a href="/ccs/member.do?page=1" class="btn btn-outline-success">직원목록</a>
							<a href="/ccs/adminapproval.do?page=1" class="btn btn-outline-success">결제서류</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<h2 style="font-weight: bolder">직원 목록</h2>
						</div>
						<div class="col-md-12">
							<hr class="col-md-12">
						</div>
						<span class="total col-md-12">전체 사원수 : ${userPage.total }</span>
						<br>
						<br>
						<div class="col-md-12">
							<table class="col-md-12 table table-striped">
								<thead>
									<tr>
										<th width="">이름</th>
										<th width="">사번</th>
										<th width="">아이디</th>
										<th width="">소속</th>
										<th width="">전화번호</th>
										<th width="">이메일</th>
									</tr>
								</thead>
								<c:forEach var="user" items="${userPage.userList }">
									<tr>
										<td>${user.name }</td>
										<td>${user.no}</td>
										<td>${user.id }</td>
										<td>${user.dept }</td>
										<td>${user.phone}</td>
										<td>${user.email}</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="6" align="center">
										<c:if test="${userPage.hasBoard() }">
											<ul class="pagination justify-content-center">
												<c:if test="${userPage.startPage > 10 }">
													<li class="page-item"><a class="page-link" href="/ccs/member.do?page=${userPage.startPage - 10}"><<</a></li>
												</c:if>
								
												<c:forEach var="pNo" begin="${userPage.startPage }" end="${userPage.endPage }">
													<li class="page-item"><a class="page-link" href="member.do?page=${pNo }">${pNo }</a></li>
												</c:forEach>
								
												<c:if test="${userPage.endPage != userPage.totalPage }">
													<li class="page-item"><a class="page-link" href="/ccs/member.do?page=${userPage.startPage + 10}">>></a></li>
												</c:if>
											</ul>
										</c:if>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>