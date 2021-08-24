package com.hgs.approve.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.approve.model.ApproveDAO;
import com.hgs.approve.model.ApproveVO;

public class AdminApproveDetail implements AService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userInfo") == null) {
			try {
				session.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			String a_no = request.getParameter("a_no");
			ApproveVO approDetail = new ApproveVO();
			ApproveDAO dao = ApproveDAO.getInstance();
			approDetail = dao.getDetailApprove(a_no);
			
			session.setAttribute("approDetail", approDetail);
		}
	}
}
