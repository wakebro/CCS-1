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
							<h2 style="font-weight: bolder">결재요청</h2>
						</div>
						<div class="col-md-12">
							<hr>
						</div>
						<br>
						<br>
						<div class="col-md-12">
							<form action="approval_proc.do" method="post">
								<table class="table table-bordered" border="1">
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
						</div>
						<div class="col-md-12">
							<hr>
						</div>
						<br>
						<br>
						<br>
						<br>
						<div class="col-md-12">
							<h2 style="font-weight: bolder">요청 기록</h2>
						</div>
					</div>
					<div class="col-md-12">
						<hr>
					</div>
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th style="width: 90px">상태</th>
									<th style="width: 90px">종류</th>
									<th style="width: 130px">발생일</th>
									<th style="width: 130px">만료일</th>
									<th style="width: 150px">메모</th>
									<th style="width: 90px">결제자</th>
								</tr>
							</thead>
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
										<ul class="pagination justify-content-center">
											<c:if test="${approveDTO.startPage > 10 }">
												<li class="page-item"><a class="page-link"
														href="approval.do?page=${approveDTO.startPage - 10 }">
														<<</a>
												</li>
											</c:if>

											<c:forEach var="pNo" begin="${approveDTO.startPage }"
												end="${approveDTO.endPage }">
												<li class="page-item"><a class="page-link"
														href="approval.do?page=${pNo }">${pNo}</a></li>
											</c:forEach>

											<c:if test="${approveDTO.endPage > approveDTO.totalPages }">
												<li class="page-item"><a class="page-link"
														href="approval.do?page=${approveDTO.startPage + 10 }">>></a>
												</li>
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
</body>
</html>