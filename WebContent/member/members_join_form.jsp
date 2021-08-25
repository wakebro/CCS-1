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
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script>
function idCheck(){
	alert("idCheck!");
	
	window.open("idCheck.jsp", "idwin", "witdh=400, height=150"); 
}
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
<div class="container">
	<h1 class="text-primary">회원가입 창</h1>
		<form action="/ccs/memberjoin.do"method="post" name="regForm">
			<div class="form-group">
				<label class="col-md-2 control-label">아이디</label>
					<div class="col-md-8">
						<input type="text" name="m_id" id="m_id" class="form-control onlyAlphabetAndNumber" placeholder="Id" required>
    				</div>
    				<input type="button" value="중복확인" class="col-md-2 btn btn-primary" onclick="idcheck()">
   			</div>
			<div class="form-group">
	      		<label class="col-md-2 control-label">비밀번호</label>
      				<div class="col-md-10">
	      				<input type="password" name="m_pw" id="mpw" onchange="check_pw()" class="form-control" placeholder="소문자+특수문자(숫자포함 6~16)"><br/>
					</div>
			</div>
			<div class="form-group">
	      		<label class="col-md-2 control-label">비밀번호 확인:</label>
	      			<div class="col-md-10">
						<input type="password" name="m_pw1" id="repw" class="form-control" placeholder="비밀번호 확인" onchange="check_pw()">&nbsp;<span id="check"></span><br/>
					</div>
			</div>
			<div class="form-group">
      			<label class="col-md-2 control-label">이름:</label>
      				<div class="col-md-10">
						<input type="text" name="m_name" class="form-control" placeholder="이름"><br/>
					</div>
			</div>
			<div class="form-group">
      			<label class="col-md-2 control-label">부서</label>
      				<div class="col-md-10">
						<select  class="form-select" aria-label="Default select example" name="dept_no" style="width: 100%">
							<c:forEach var="dept" items="${deptlist}">
								<option value=${dept.dept_no }>${dept.dept_name}</option>
							</c:forEach></select>
					</div>
			</div>
			<div class="form-group">
      			<label class="col-md-2 control-label">핸드폰번호:</label>
      				<div class="col-md-10">
						<input type="tel" class="form-control" name="m_phone" id="m_phone" value="000-0000-0000" maxlength=13><br/>
					</div>
			</div>
			<div class="form-group">
      			<label class="col-md-2 control-label">이메일:</label>
      				<div class="col-md-10">
						<input type="email" name="m_email" id="m_email" class="form-control" placeholder="이메일"><br/>
					</div>
			</div>
			<input type="submit" class="btn btn-primary" value="가입">
	</form>
</div>
</body>
</html>