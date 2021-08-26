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
					<div class="col-md-10 offset-md-1">
						<table class="content table table-bordered border border-success">
							<tr>	
								<td class="col-md-2"><strong>글 번호</strong></td>
								<td class="col-md-4">${board.b_no }</td>
								<td class="col-md-2"><strong>조회수</strong></td>
								<td class="col-md-4">${board.b_view }</td>
							</tr>
							<tr>	
								<th class="col-md-2"><strong>글쓴이</strong></th>
								<td class="col-md-4">${board.m_id }</td>
								<td class="col-md-2"><strong>게시일</strong></td>
								<td class="col-md-4" colspan="4">${board.b_date }</td>
							</tr>
							<tr>	
								<th class="col-md-2"><strong>제목</strong></th>
								<td class="col-md-10" colspan="4">${board.b_title }</td>
							</tr>
							<tr>	
								<td scope="row" colspan="4">
									<textarea class="form-control-plaintext col-md-12" rows="20px" cols="60px" readonly>${board.b_content }</textarea>
								</td>
							</tr>
						</table>
					</div>
					<div class="col-md-10 offset-md-1">
						<!-- 자신이 올린 게시물만 수정과 삭제 버튼이 보이게 하는 로직 -->
						<div class="row">
							<c:choose>
								<c:when test="${ writer == 1}">
									<form class="col-md-2 offset-md-2" style='display: inline' action="/ccs/boardUpdate.do" method="post">
										<input type="hidden" value="${board.b_no }" name="b_no" /> 
										<input class="btn btn-outline-success" type="submit" value="수정하기" />
									</form>
		
									<form class="col-md-2" style='display: inline' action="/ccs/boardDelete.do" method="post">
										<input type="hidden" value="${board.b_no }" name="b_no" />
										<input class="btn btn-outline-success" type="submit" value="삭제하기" />
									</form>
									<button class="btn btn-outline-success col-md-3" type="button" onclick="location.href='/ccs/board.do'">리스트로 돌아가기</button>
								</c:when>
								<c:otherwise>
									<button class="btn btn-outline-success col-md-4 offset-md-4" type="button" onclick="location.href='/ccs/board.do'">리스트로 돌아가기</button>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>