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
<style type="text/css">
$container-max-widths: (
  sm: 540px,
  md: 720px,
  lg: 960px,
  xl: 1140px,
  xxl: 1320px
);
</style>
<meta charset="UTF-8">

<title>출근</title>
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
	<div class="container-fluid">
	<h2>출근시 입력창</h2>

	<form action="/ccs/doCommute.do" method="post">
		<div style="border: 1px solid; width: 1485px; height: 100%;">
			<table style="width: 100%; padding:10% 35%; ">
				<input type="hidden" id="inTime" name="inTime" value="${inOutVO.getClock_in_time()}">
				<tr>	
					<td>이름 / 부서 : ${userInfo.m_Name } / ${userInfo.dept }
				</tr>
				<tr>
				</tr>
				<tr>
					<td>오늘의 출근시간 : ${inOutVO.getClock_in_time()}</td>
				</tr>
				<tr>
					<td>오늘의 퇴근시간 : ${inOutVO.getClock_out_time()}</td>
				</tr>
				<tr style="height: 100px;">
					<td>
						<c:if test="${empty inOutVO.clock_in_time}">
							<input type="submit" value="출근하기"> 
						</c:if>
						<c:if test="${not empty inOutVO.clock_in_time && empty inOutVO.clock_out_time}">
							<input type="submit" value="퇴근하기">
						</c:if>
					</td>
				</tr>
			</table>
		</div>
		
	<!-- 출결목록 -->
		<table class="table table-striped">
			<tr class="even">
			    <th scope="col">사원번호</th>
			    <th>이름</th>
				<th>출근시간</th>
				<th>퇴근시간</th>
			</tr>
		<c:forEach var="commute" items="${commuteList}" >
			<tr>
			    <td>${commute.m_no}</td>
			    <td>${commute.m_name}</td>
			    <td>${commute.clock_in_time}</td>
			    <td>${commute.clock_out_time}</td>
			</tr>
		</c:forEach>
		</table>
			

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
	<c:forEach var="pNo" begin="${inOutDTO.startPage}" end="${inOutDTO.endPage}">
		<li class="page-item"><a class="page-link" href="/ccs/commute.do?page=${pNo}" >${pNo }</a></li>
	</c:forEach>
	
<!-- 다음으로 가기 버튼을 표시할지 말지 결정하는 부분 -->
<li class="page-item">
	<c:if test="${inOutDTO.endPage < inOutDTO.totalPages}">
		<a class="page-link" href="/ccs/commute.do?page=${inOutDTO.startPage + 5}" 
			aria-label="Next">
			<span aria-hidden="true">&raquo;</span>
		</a>
	</c:if>
	</li>
	</ul>
	</nav>
	</c:if>
<!-- 페이징 부분 끝 -->
			
	</form>
	</div>
</body>
</html>