package com.hgs.user.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.user.model.UserDAO;
import com.hgs.user.model.UserVO;

public class LoginService implements UService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String id = request.getParameter("login_id");
		String pw = request.getParameter("login_pw");
		UserVO userInfo = null;

		if (id == null) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			UserDAO dao = UserDAO.getInstace();
			UserVO user = new UserVO();
			user.setId(id);
			user.setPw(pw);
			userInfo = dao.login(user);
			String userDept = dao.getUserDept(userInfo.getDept_no());
			
			if(userInfo.getId() == null) {
				try {
					session.invalidate();
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("userDept", userDept);
			session.setAttribute("session_id", id);
			
		}
	}
}
