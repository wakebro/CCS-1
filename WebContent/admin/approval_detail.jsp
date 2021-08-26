<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
						<h2 style="font-weight: bolder;">결재 내용</h2>
						<form action="/ccs/adminapproconfirm.do" method="post">
							<table class="table table-bordered">
								<tr>
									<th><strong>No.</strong></th>
									<td style="width: 30px">
										<input type="hidden" name="a_no" value="${approDetail.a_no }" readonly="readonly">
										${approDetail.a_no}
									</td>
									<th><strong>종류</strong></th>
									<td>${approDetail.a_category }</td>
									<th><strong>소속</strong></th>
									<td>${approDetail.d_name }</td>
								</tr>
								<tr>
									<th><strong>사번</strong></th>
									<td>${approDetail.m_no }</td>
									<th><strong>이름</strong></th>
									<td>${approDetail.m_name }</td>
									<th><strong>결제자</strong></th>
									<td>${approDetail.a_head }</td>
								</tr>
								<tr>
									<th><strong>발생일</strong></th>
									<td colspan="2">${approDetail.a_start_ }</td>
								</tr>
								<tr>
									<th><strong>만료일</strong></th>
									<td colspan="2">${approDetail.a_end_ }</td>
								</tr>
								<tr>
									<th><strong>상태</strong></th>
									<td colspan="5" style="text-align: center;">
										<c:choose>
											<c:when test="${approDetail.a_status.equals(\"대기\") }">
												<input type="radio" name="a_status" checked="checked" value="대기">대기
												<input type="radio" name="a_status" value="승인">승인
												<input type="radio" name="a_status" value="비승인">비승인
											</c:when>
											<c:when test="${approDetail.a_status.equals(\"승인\") }">
												<input type="radio" name="a_status" value="대기">대기
												<input type="radio" name="a_status" checked="checked" value="승인">승인
												<input type="radio" name="a_status" value="비승인">비승인
											</c:when>
											<c:when test="${approDetail.a_status.equals(\"비승인\") }">
												<input type="radio" name="a_status" value="대기">대기
												<input type="radio" name="a_status" value="승인">승인
												<input type="radio" name="a_status" checked="checked" value="비승인">비승인
											</c:when>
										</c:choose>
									</td>
								</tr>
								<tr>
									<th><strong>메모</strong></th>
									<td colspan="5">
										<textarea name="a_reason" rows="30px" cols="50px" class="col-md-12">
											${approDetail.a_reason }
										</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="6" style="text-align: center">
										<input type="submit" class="btn btn-outline-success" value="결제">
									</td>
								<tr>
							</table>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>