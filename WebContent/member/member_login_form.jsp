<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<style>
			* {
				font: 15px "굴림", Gulim;
			}

			header {
				padding-top: 20px;
			}

			#username {
				font-family: 'Black Han Sans', sans-serif;
				font-size: 300%;
			}

			.body {
				height: 100vh;
				background-color: rgb(235, 232, 232);
			}

			.logo {
				width: 200px;
				padding-top: 20px;
				padding-right: 35px;
			}

			.userInfo {
				display: flex;
				flex-direction: column;
				justify-content: flex-end;
				margin-bottom: 10px;
			}

			.sider {
				height: 200px;
			}

			.header-hr {
				margin-top: 0;
			}

			#joinBtn {
				display: inline-block;
			}
		</style>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
		<meta charset="UTF-8">
		<title>CCS - Company Commute System</title>
	</head>

	<body>
		<form action="/ccs/login.do" method="post">
			<div class="container">
				<header>
					<div class="row">
						<div class="col-md-2">
							<div class="logo row">
								<img src="../img/logo.png" onerror="this.src='../ccs/img/logo.png'">
							</div>
						</div>
					</div>
				</header>
				<hr class="header-hr">
				<div id="header"></div>
				<h1>LOGIN</h1>

				<div class="row md-2">
					<div class="form-floating">
						<input type="text" class="form-control" name="m_id" id="m_id" placeholder="ID"
							style="width:300px">
						<label for="floatingInput">&nbsp;아이디를 입력하세요.</label>
					</div>
					<div class="row md-2">
						<div class="form-floating">
							<input type="password" class="form-control" name="m_pw" id="m_pw" maxlength=12
								placeholder="Password" style="width:300px">
							<label for="floatingPassword">&nbsp;비밀번호를 입력하세요.</label>
						</div>
					</div>
					<div class="row" style="display: inline-block; margin-top:10px; margin-left:3px">
						<input type="submit" class="btn btn-success" value="로그인" style="width:100px">
						<a href="/ccs/join.do">
							<input type="button" id="joinBtn" class="btn btn-success" value="회원가입"
								style="width:100px"></a>
					</div>
				</div>
			</div>
		</form>
	</body>

	</html>