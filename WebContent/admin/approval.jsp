<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결재창</title>
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
						<div class="row">
							<div class="col-md-12">
								<h2 style="font-weight: bolder;">결재창</h2>
							</div>
							<div class="col-md-12">
								<hr class="col-md-12">
							</div>
							<br>
							<br>
							<div class="col-md-12">
								<table class="table table-striped">
									<thead>
										<tr>
											<th style="width: 50px">No.</th>
											<th style="width: 50px">종류</th>
											<th style="width: 90px">소속</th>
											<th style="width: 50px">사번</th>
											<th style="width: 90px">이름</th>
											<th style="width: 90px">상태</th>
										</tr>
									</thead>
									<c:forEach var="list" items="${approveAllDTO.approveList }">
										<tr>
											<td>${list.a_no }</td>
											<td>${list.a_category }</td>
											<td>${list.d_name}</td>
											<td>${list.m_no}</td>
											<td><a href="/ccs/adminapprodetail.do?a_no=${list.a_no }">${list.m_name}</a></td>
											<td>${list.a_status}</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="10" style="text-align: center">
											<c:if test="${approveAllDTO.hasList() }">
												<ul class="pagination justify-content-center">
													<c:if test="${approveAllDTO.startPage > 10 }">
														<li class="page-item"><a class="page-link" href="adminapproval.do?page=${approveAllDTO.startPage - 10 }"><<</a></li>
													</c:if>
													<c:forEach var="pNo" begin="${approveAllDTO.startPage }" end="${approveAllDTO.endPage }">
														<li class="page-item"><a class="page-link btn-outline-primary" href="adminapproval.do?page=${pNo }">${pNo}</a></li>
													</c:forEach>
													<c:if test="${approveAllDTO.endPage > approveAllDTO.totalPages }">
														<li class="page-item" ><a class="page-link" href="adminapproval.do?page=${approveAllDTO.startPage + 10 }">>></a></li>
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

	</div>
</body>
</html>