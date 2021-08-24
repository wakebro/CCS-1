package com.hgs.user.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.user.model.UserDAO;
import com.sjh.model.MemberVO;

public class UpdateService implements UService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("session_id") == null || session.getAttribute("session_id").equals("")) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			UserDAO dao = UserDAO.getInstace();
			MemberVO user = new MemberVO();
			
			user.setM_Name(request.getParameter("update_name"));
			user.setM_No(Integer.parseInt(request.getParameter("update_no")));
			user.setM_Id(request.getParameter("update_id"));
			user.setM_Pw(request.getParameter("update_pw"));
			user.setDept_no(Integer.parseInt(request.getParameter("update_dept")));
			user.setM_Phone(request.getParameter("update_phone"));
			user.setM_Email(request.getParameter("update_email"));
			System.out.println(user);
			dao.update(user);
			MemberVO userInfo = dao.login(user); 
			
			session.setAttribute("userInfo", userInfo);
		}
	}
}
