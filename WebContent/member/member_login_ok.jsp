<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
=======
<%@page import="com.sjh.model.MemberVO"%>
<%@page import="com.sjh.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

// 0. post방식 받아오기 처리
  	response.setCharacterEncoding("utf-8");
	request.setCharacterEncoding("utf-8");  	

  	// 1. 아이디 비밀번호 받아오기
 	String m_id = request.getParameter("m_id");
  	String m_pw = request.getParameter("m_pw");

  // if~else문으로 try~catch~finally를 감싸서
  // 세션이 존재할 때는 DB에서 데이터를 가져오는 로직을 생략해주세요.
  	String idSession = (String)session.getAttribute("i_s");
  	if(idSession != null){
  		m_id = idSession;
  	} else{
  		// 1. dao 생성
  		MemberDAO dao = MemberDAO.getinstance();
  		
  		// 2. dao로 로그인 검사
		MemberVO member = new MemberVO();
		member.setM_Id("m_Id");
		member.setM_Pw("m_Pw");
		
		int loginResultNum = dao.login(m_id, m_pw);
		
		if(loginResultNum == 1){
			session.setAttribute("i_s", m_id);
			session.setAttribute("p_s", m_pw);
		} else if(loginResultNum == 0){
			response.sendRedirect("member_login_form.jsp");
		}
  		
  	}
 %>
>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<<<<<<< HEAD
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
	
	
	
=======
	<%=m_id %>님, 환영합니다.
	<h1>로그인이 완료되었습니다.</h1>
	<a href="member_logout.jsp">로그아웃하기</a><br/>
	<a href="member_berinfo.jsp">사원 정보 보기</a>
	<a href="/ccs/memberselete.do">사원 전체 목록</a>
>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
</body>
</html>