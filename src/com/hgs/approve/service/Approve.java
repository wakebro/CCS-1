package com.hgs.approve.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.approve.model.*;

public class Approve implements AService {
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
		}else {
			List<A_CategoryVO> categoryList = new ArrayList<A_CategoryVO>();
			A_CategoryDAO dao = A_CategoryDAO.getInstance();
			categoryList = dao.getCategory();
			session.setAttribute("categoryList", categoryList);
		}
	}
}
