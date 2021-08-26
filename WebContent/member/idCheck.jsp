
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복체크</title>
<body>
 <div style="text-align: center">
 <h3>아이디 중복확인</h3>
<form method="post" action="idCheckAction.jsp" onsubmit="return blankCheck(this)">
아이디: <input type="text" name="m_id" maxlength="10" actofocus>
<input type="submit" value="중복확인">
</form>
</div>

<script>
function blankCheck(f){
	var id = f.id.value;
	id=id.trim();
	if(id.length<5){
		alert("아이디는 5자 이상 입력해주십시오.");
		return false;
	}
	return true;
} //blackCheck() end
</script>
</body>
</html>