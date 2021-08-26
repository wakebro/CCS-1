<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>CCS - Article 등록</title>
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
	.content {
		margin-top: 20px;
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
							<span id="username">${userInfo.m_Name }</span>&nbsp;${userInfo.dept }/<a
								href="/ccs/logout.do">로그아웃</a>
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
					<a href="/ccs/board.do?page=1" class="btn btn-success" role="button">게시판</a> <a
						href="/ccs/userinfo.do" class="btn btn-success" role="button">My Page</a> <a
						href="/ccs/approval.do" class="btn btn-success" role="button">결재창</a>
					<c:set var="dept" value="${admin }"></c:set>
					<c:if test="${dept eq '관리자' }">
						<a href="/ccs/admin.do" class="btn btn-success" role="button">관리자</a>
					</c:if>
				</div>
				<div class="col-md-10">
					<div class="col-md-12">
						<h2 style="font-weight: bolder">글 쓰기</h2>
					</div>
					<div class="col-md-12">
						<hr>
					</div>
					<div class="col-md-8 offset-md-1">
						<form action="/ccs/boardCreate.do" method="post">
							<table class="content table table-bordered border border-success">
								<tr>
									<td><input type="text" class="form-control" name="title" placeholder="제목을 입력해주세요" required="required"></td>
								</tr>
								<tr>
									<td><textarea cols="70" rows="20" name="content" required="required"></textarea></td>
								</tr>
								<tr>
									<td><input type="text" class="form-control-plaintext col-md-12" name="id" value="${session_id}" readonly></td>
								</tr>
							</table>
							<p>
								<button type="button" class="btn btn-outline-success col-md-2 offset-md-4" onclick="location.href='/ccs/board.do'">취소</button>&nbsp;
								<input type="submit" class="btn btn-outline-success col-md-2" value="등록">
							</p>		
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>