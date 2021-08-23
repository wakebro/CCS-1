<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
table.commute {
  border-collapse: collapse;
  text-align: center;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  margin: 20px 10px;
}
table.commute th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border: 1px solid #ccc;
}
table.commute td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border: 1px solid #ccc;
}
table.commute .even {
  background: #efefef;
}

</style>
<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<title>출근</title>
</head>
<body>

<script>

 	$(document).ready(function(){


 	});	

	function commute(m_no){
		alert('환영합니다.' + m_no);
	}

</script>

<h2>출근시 입력창</h2>

	<form action="/ccs/doCommute.do" method="post">
		<div style="border: 1px solid; width: 1485px; height: 100%;">
			<table style="width: 100%; padding:10% 35%; ">
				<input type="hidden" id="inTime" name="inTime" value="${inOutVO.getClock_in_time()}">
				<tr>	
					<td>이름 / 부서 : session_name / session dept_nm</td>
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
			<table class="commute">
			  <tr class="even">
			    <th scope="col">사원번호</th>
			    <th>이름</th>
				<th>출근시간</th>
				<th>퇴근시간${commuteListCount }</th>
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
	<c:if test="${InOutDTO.hasBoard() }">
<!-- 뒤로 가기(전으로 가기) 버튼 표시할지 판단여부  -->
	<c:if test="${InOutDTO.startPage > 5}">
		<a href="/ccs/commute.do?page=${InOutDTO.startPage - 5}">[prev]</a>
	</c:if>
	
	
<!-- 페이지 번호 5개 묶음을 깔아주는 부분 -->
	<c:forEach var="pNo" begin="${InOutDTO.startPage}" end="${InOutDTO.endPage}">
		<a href="/ccs/commute.do?page=${pNo}">[${pNo }]</a>
	</c:forEach>
	
<!-- 다음으로 가기 버튼을 표시할지 말지 결정하는 부분 -->
	<c:if test="${InOutDTO.endPage < InOutDTO.totalPages}">
		<a href="/ccs/commute.do?page=${InOutDTO.startPage + 5}">[next]</a>
	</c:if>
	
	</c:if>
<!-- 페이징 부분 끝 -->
			
	</form>
	
</body>
</html>