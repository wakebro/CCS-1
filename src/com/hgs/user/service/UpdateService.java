package com.hgs.user.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.user.model.UserDAO;
import com.hgs.user.model.UserVO;

public class UpdateService implements UService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("session_id") == null || session.getAttribute("session_id").equals("")) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			UserDAO dao = UserDAO.getInstace();
			UserVO user = new UserVO();
			
			user.setName(request.getParameter("update_name"));
			user.setNo(Integer.parseInt(request.getParameter("update_no")));
			user.setId(request.getParameter("update_id"));
			user.setPw(request.getParameter("update_pw"));
			user.setDept_no(Integer.parseInt(request.getParameter("update_dept")));
			user.setPhone(request.getParameter("update_phone"));
			user.setEmail(request.getParameter("update_email"));
			
			dao.update(user);
			UserVO userInfo = dao.login(user); 
			String userDept = dao.getUserDept(user.getDept_no());
			
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("userDept", userDept);
		}
	}
}
