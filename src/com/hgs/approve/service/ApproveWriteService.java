package com.hgs.approve.service;

import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.approve.model.ApproveDAO;
import com.hgs.approve.model.ApproveVO;

public class ApproveWriteService implements AService {
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
			String a_category = request.getParameter("a_category");
			Timestamp a_start = Timestamp.valueOf(request.getParameter("a_start"));
			Timestamp a_end = Timestamp.valueOf(request.getParameter("a_end"));
			String a_reason = request.getParameter("a_reason");
			
			ApproveVO approve = new ApproveVO();
			approve.setA_category(a_category);
			approve.setA_start(a_start);
			approve.setA_end(a_end);
			
			ApproveDAO dao = ApproveDAO.getInstance();
			dao.writeApprove(approve);
		}
	}
}
