<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" 
rel="stylesheet" 
integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" 
crossorigin="anonymous">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">

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
<meta charset="UTF-8">
<title>CCS - Company Commute System</title>
</head>
<body>
<form action="/ccs/doCommute.do" method="post">
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
						<h2>출결 관리</h2>
							<input type="hidden" id="inTime" name="inTime" value="${inOutVO.getClock_in_time()}">
							<div class="text-center">					
										이름 / 부서 : ${userInfo.m_Name } / ${userInfo.dept }<br/>
							오늘의 출근시간 : ${inOutVO.getClock_in_time()}<br/>
							오늘의 퇴근시간 : ${inOutVO.getClock_out_time()}</div>
							<div class="text-center">
							<c:if test="${empty inOutVO.clock_in_time}">
								<input type="submit" class="btn btn-success" value="출근하기"> 
							</c:if>
							<c:if test="${not empty inOutVO.clock_in_time && empty inOutVO.clock_out_time}">
								<input type="submit" class="btn btn-success" value="퇴근하기">
							</c:if>
							</div>		
						</div>
						<br>
						<br>
						<div>
						</div>
						<br>
						<hr>
						<!-- 출결테이블 -->
						<table class="table table-striped">
							<tr class="even">
								<th scope="col">사원번호</th>
								<th>이름</th>
								<th>출근시간</th>
								<th>퇴근시간</th>
							</tr>
						<c:forEach var="commute" items="${commuteList}">
							<tr>
								<td>${commute.m_no}</td>
								<td>${commute.m_name}</td>
								<td>${commute.clock_in_time}</td>
								<td>${commute.clock_out_time}</td>
							</tr>
						</c:forEach>
						</table><!-- 출결테이블 끝 -->
						<p>
				<%-- 페이징 버튼 만들기 // 표현할 글이 있는 경우에만 버튼을 표시함--%>
				<c:if test="${inOutDTO.hasBoard() }">
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<li class="page-item">
			
				<!-- 뒤로 가기(전으로 가기) 버튼 표시할지 판단여부  -->
				<c:if test="${inOutDTO.startPage-10 > 1}">
					<a href="/ccs/commute.do?page=${inOutDTO.startPage - 5}"
						class="page-link" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</c:if>
			
				<!-- 페이지 번호 5개 묶음을 깔아주는 부분 -->
				<c:forEach var="pNo" begin="${inOutDTO.startPage}"
					end="${inOutDTO.endPage}">
				<li class="page-item"><a class="page-link"
					href="/ccs/commute.do?page=${pNo}">${pNo }</a></li>
				</c:forEach>
			
				<!-- 다음으로 가기 버튼을 표시할지 말지 결정하는 부분 -->
				<li class="page-item">
					<c:if test="${inOutDTO.endPage < inOutDTO.totalPages}">
						<a class="page-link"
						href="/ccs/commute.do?page=${inOutDTO.startPage + 5}"
						aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
						</a>
					</c:if>
				</li>
				</ul>
				</nav>
				</c:if><!-- 페이징 부분 끝 -->
						</p>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
</body>	
</html>