package com.hgs.user.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainPageService implements UService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfo") == null) {
			try {
				session.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
