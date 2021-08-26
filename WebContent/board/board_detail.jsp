<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCS - ${board.b_title }</title>
</head>
<style>
	*{font: 15px "굴림", Gulim;}
	header{
		padding-top: 20px;
	}
	#username{
		font-weight: bolder;
		font-size: 500% ;
	}
	.body{
		height: 100vh;
		background-color: rgb(235, 232, 232);
	}
	.sider {
		height:200px;
	}
	.header-hr{
		margin-top: 0;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<body>
	<div class="container">
		<header>
			<div class="row">
				<div class="col-md-2">
					<img src="${logo }">
				</div>
				<div class="col-md-10">
					<span id="username">${userInfo.m_Name }</span> ${userInfo.dept } <a
						href="/ccs/logout.do">로그아웃</a>
				</div>
			</div>
		</header>
		<hr class="header-hr">
		<div class="body">
			<div class="row">
				<div class="sider btn-group-vertical col-md-2">
					<a href="/ccs/commute.do" class="btn btn-success" role="button">메인화면</a>
					<a href="/ccs/board.do?page=1" class="btn btn-success"
						role="button">게시판</a> <a href="/ccs/userinfo.do"
						class="btn btn-success" role="button">My Page</a> <a
						href="/ccs/approval.do" class="btn btn-success" role="button">결재창</a>
					<c:set var="dept" value="${admin }"></c:set>
					<c:if test="${dept eq '관리자' }">
						<a href="/ccs/admin.do" class="btn btn-success" role="button">관리자</a>
					</c:if>
				</div>
				<div class="col-md-10">
				<table>
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
						<td><textarea cols="50" rows="20" readonly="readonly">${board.b_content }</textarea></td>
					</tr>
				</table>
					<p>
						<!-- 자신이 올린 게시물만 수정과 삭제 버튼이 보이게 하는 로직 -->
						<c:if test="${ writer == 1}">
							<form style='display: inline' action="/ccs/boardUpdate.do"
								method="post">
								<input type="hidden" value="${board.b_no }" name="b_no" /> <input
									type="submit" value="수정하기" />
							</form>

							<form style='display: inline' action="/ccs/boardDelete.do"
								method="post">
								<input type="hidden" value="${board.b_no }" name="b_no" /> <input
									type="submit" value="삭제하기" />
							</form>
						</c:if>
						<button type="button" onclick="location.href='/ccs/board.do'">리스트로
							돌아가기</button>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>