package com.sjh.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.user.model.UserDAO;
import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;


public class MemberLoginService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			
			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
			
			if (m_id == null) {
				try {
					RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
			
			MemberDAO dao = MemberDAO.getinstance();
			MemberVO user = new MemberVO();
			MemberVO userInfo = new MemberVO();
			UserDAO uDao = UserDAO.getInstace();
			
			user.setM_Id(m_id);
			user.setM_Pw(m_pw);
			userInfo = dao.login(user);
			// userInfo.setDept(uDao.getUserDept(userInfo.getDept_no()));
			userInfo.setDept("125");
			String userDept = user.getDept();
			
			// 로그인
			if(userInfo.getM_Id() == null) {
				try {
					session.invalidate();
					RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(userInfo.getDept_no()==1000) {
				session.setAttribute("admin", userInfo.getDept());
			}
			// 세션 생성
			session.setAttribute("session_id", userInfo.getM_Id());
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("userDept", userDept);
		}
	}	
}
