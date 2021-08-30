<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
html {
    height: 100%;
}

body {
    margin: 0;
    height: 100%;
}
#logo {
    width: 240px;
    height: 44px;
    cursor: pointer;
}

#header {
    padding-top: 62px;
    padding-bottom: 20px;
    text-align: center;
}
#wrapper {
    position: relative;
    height: 100%;
}

#content {
    position: absolute;
    left: 50%;
    transform: translate(-50%);
    width: 460px;
}
.box {
    display: block;
    width: 100%;
    height: 51px;
    border: solid 1px #dadada;
    padding: 10px 14px 10px 14px;
    box-sizing: border-box;
    background: #fff;
    position: relative;
}
.int {
    display: block;
    position: relative;
    width: 100%;
    height: 29px;
    border: 1px solid silver;
    background: #fff;
    font-size: 15px;
}
.btn_area {
    margin: 30px 0 91px;
}

#btnJoin {
    width: 100%;
    padding: 21px 0 17px;

    border: 0;
    cursor: pointer;
    color: #fff;
    background-color: #198754;
    font-size: 20px;
    font-weight: 400;
}
select {
    width: 100%;
    height: 29px;
    font-size: 15px;
    background: #fff 100% 50% no-repeat;
    background-size: 20px 8px;
    display: inline-block;
    text-align: start;
    border: none;
    cursor: default;
}
check{
	color: blue;
}
</style>
<meta charset="UTF-8">
<title>CCS - 회원가입</title>
</head>
<script>
	function check_pw(){
		
		var pw = document.getElementById('mpw').value;
        var SC = ["!","@","#","$","*"];
        var check_SC = 0;

        if(pw.length < 6 || pw.length > 16){
            window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
            document.getElementById('mpw').value='';
        }
        for(var i=0;i<SC.length;i++){
            if(pw.indexOf(SC[i]) != -1){
                check_SC = 1;
            }
        }
        if(check_SC == 0){
            window.alert('!,@,#,$,* 의 특수문자가 들어가 있지 않습니다.')
            document.getElementById('mpw').value='';
        }
        if(document.getElementById('mpw').value !='' && document.getElementById('repw').value!=''){
            if(document.getElementById('mpw').value==document.getElementById('repw').value){
                document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                document.getElementById('check').style.color='blue';
            }
            else{
                document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                document.getElementById('check').style.color='red';
			}
		}
	}
	
</script>
<body>
<form action="/ccs/memberjoin.do"method="post" name="regForm">
	<!-- CSS 배너 -->
	<div id="header">
	<img src="../img/logo.png" onerror="this.src='../ccs/img/logo.png'">
	</div>
	<div class="wrapper">
		<!-- Container -->
		<div class="container">
		<div id="content">
			<!-- 아이디, 비밀번호 입력 -->
      		<div class="mb-3">
	         	<h3 class="join_title"><label for="m_id" class="form-label">아이디</label></h3>
	         	<span class="ps_box box_right_space">
			 <input type="text" name="m_id" id="m_id" class="form-control int" title="ID" maxlength="20" required>
            	</span>
            </div>

            <div class="join_row">
             	<h3 class="join_title"><label for="m_pw">비밀번호</label></h3>
            	<span class="ps_box int_pass" id="m_pw">
			<input type="password" name="m_pw" id="mpw" class="int" title="비밀번호 입력"  maxlength="16" onchange="check_pw()" placeholder="소문자+특수문자(숫자포함 6~16)">
				</span>

                    <h3 class="join_title"><label for="repw">비밀번호 재확인</label></h3>
                    <span class="ps_box int_pass_check" id="m_pw1">
			<input type="password" name="m_pw1" id="repw" class="int" title="비밀번호 재확인 입력"  onchange="check_pw()">&nbsp;<span id="check"></span>
					</span>
            </div>
			<!-- // 아이디, 비밀번호 입력 끝 -->
	
			<!-- 이름, 부서, 번호, 이메일 입력 -->
			<div class="row_group">
				<div class="join_row">
			<h3 class="join_title"><label for="name">이름</label></h3>
			<span class="ps_box box_right_space">
				<input type="text" id="name" name="m_name" class="int" title="이름" maxlength="10">
			</span>
			 
			</div>
			<div class="form-floating">
			<h3 class="join_title">부서</h3>
			<div class="join_row" style="border: solid 1px silver; width: 100%;">
			<select class="form-select" id="floatingSelect" name="dept_no" title="부서" aria-label="Floating label select example">
			    <option selected>부서를 선택하세요.</option>
				<c:forEach var="dept" items="${deptlist}">
			    <option value=${dept.dept_no }>${dept.dept_name}</option>
			  	</c:forEach>
			</select>
			  
			</div>
			</div>
			<div class="join_row"> 
			<h3 class="join_title"><label for="m_phone">휴대전화</label></h3> 
				<span class="ps_box box_right_space">	
				<input type="tel" name="m_phone" id="m_phone" class="int" title="휴대전화" placeholder="000-0000-0000" maxlength=13><br/> 
				</span> 
			</div> 
			<div class="join_row"> 
				<h3 class="join_title"><label for="m_email">이메일</label></h3> 
				<input type="email" name="m_email" id="m_email" title="이메일" class="int"><br/> 
			</div> 
	
			<div class="btn_area"> 
			<button type="submit" id="btnJoin" class="btn btn-secondary"><span>가입하기</span></button>  
			</div> 
			</div> 
		</div><!-- // 이름, 부서, 번호, 이메일 입력 끝 --> 
		</div><!-- // container 끝 --> 
	</div> 
</form> 
</body> 
</html>