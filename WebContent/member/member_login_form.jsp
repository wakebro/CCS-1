<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" 
	rel="stylesheet" 
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" 
	crossorigin="anonymous">
<style>
.container {
        width: 940px;
        margin: 0px auto;
        padding: 20px;
        border: 1px solid #bcbcbc;
}
.header{
	padding: 20px;
        margin-bottom: 20px;
        border: 1px solid #bcbcbc;
}
.sidebar{
        width: 260px;
        padding: 20px;
        margin-bottom: 20px;
        float: right;
        border: 1px solid #bcbcbc;
}
.content {
        width: 580px;
        padding: 20px;
        margin-bottom: 20px;
        float: left;
        border: 1px solid #bcbcbc;
}
der .h_logo{
	display: blue;
	overflow: hidden;
	width: 231px;
	height: 44px;
	margin: 0 auto;
	background-position-x: -1px;
	background-position-y: -1px;
	font-size: 15px;
	color: transparent;
}
der .c_logo:before{
	content: '\00a1';
	display : blue;
	font-size:0;
	line-height: 0;
}	

</style>
<meta charset="UTF-8">
<title>로그인</title>

</head>
<body>
<div class = "container">
<header>
<div id = "header"></div>
	<h1>
		<a href="https://www.naver.com" class="p c_logo" id="log.naver">
		<span class="blian">CCS</span>
		</a>
	</h1>
</header>
<div class="row">
<div class="col-4 sidebar">side</div>
	<form action="/ccs/login.do" method="post">
	<div class="col-8">
			<div class="row">
				<label for="ID" class="col-md-2 control-label">아이디</label>
					<div class="col-md-4">
						<input type="text" name="m_id" id="m_id" class="form-control form-control" placeholder="ID">
					</div>
			</div>
			<div class="row">
				<label for="password" class="col-md-2 control-label">비밀번호</label>
					<div class="col-md-4">
						<input type="password" name="m_pw" id="m_pw" class="form-control form-control" maxlength=12 placeholder="PASSWORD">
					</div>
			</div>
				<input type="submit"  class="btn btn-primary" value="로그인">
					<a href="/ccs/join.do">
						<input type="submit"  class="btn btn-primary"value="회원가입"></a>
		</div>
	</form>
</div>	
</div>
</body>
</html>