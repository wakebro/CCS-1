<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD:WebContent/member/member_login_fail.jsp
   
=======
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
>>>>>>> yjw:WebContent/board/board_detail.jsp
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" 
rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
 crossorigin="anonymous">
<meta charset="UTF-8">
<title>CCS - ${board.b_title }</title>
</head>
<body>
<<<<<<< HEAD:WebContent/member/member_login_fail.jsp
	<h1>로그인에 실패했습니다.</h1>
	<a href="member_login_form.jsp">로그인창</a>
=======
	<table border="1">
		<tr>	
			<td>${board.b_no }</td>
			<th>글 번호</th>
		</tr>
		<tr>	
			<td>${board.b_view }</td>
			<th>조회수</th>
		</tr>
		<tr>	
			<td>${board.m_id }</td>
			<th>글쓴이</th>
		</tr>
		<tr>	
			<td>${board.b_date }</td>
		</tr>
		<tr>	
			<td>${board.b_title }</td>
		</tr>
		<tr>	
			<td><textarea cols="50" rows="20">${board.b_content }</textarea></td>
		</tr>
	</table>
	<p>
		<!-- 자신이 올린 게시물만 수정과 삭제 버튼이 보이게 하는 로직 -->
		<c:if test="${ writer == 1}"> 	
			<form style='display:inline' action="/ccs/boardUpdate.do" method="post">
				<input type="hidden" value="${board.b_no }" name="b_no" />
				<input type="submit" value="수정하기" />
			</form>&nbsp;
			
			<form style='display:inline' action="/ccs/boardDelete.do" method="post">
				<input type="hidden" value="${board.b_no }" name="b_no" />
				<input type="submit" value="삭제하기" />
			</form>&nbsp;
		</c:if>
		<button type="button" onclick="location.href='/ccs/board.do'">리스트로 돌아가기</button>&nbsp;
	</p>
>>>>>>> yjw:WebContent/board/board_detail.jsp
</body>
</html>