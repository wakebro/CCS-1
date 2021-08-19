package com.hgs.approve.service;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.approve.model.ApproveDAO;
import com.hgs.approve.model.ApproveVO;
import com.hgs.user.model.UserVO;

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
			try {
			UserVO userInfo = (UserVO)session.getAttribute("userInfo");
			SimpleDateFormat timestamp_ = new SimpleDateFormat("yyyy-MM-dd");
				
			Date D_a_start = timestamp_.parse(request.getParameter("a_start"));
			Date D_a_end = timestamp_.parse(request.getParameter("a_end"));
			Timestamp a_start = new Timestamp(D_a_start.getTime());
			Timestamp a_end = new Timestamp(D_a_end.getTime());
			String a_category = request.getParameter("a_category");
			String a_reason = request.getParameter("a_reason");
			String d_name = userInfo.getDept();
			String m_name = userInfo.getName();
			int m_no = userInfo.getNo();

			ApproveVO approve = new ApproveVO();
			approve.setA_category(a_category);
			approve.setA_start(a_start);
			approve.setA_end(a_end);
			approve.setA_reason(a_reason);
			approve.setD_name(d_name);
			approve.setM_no(m_no);
			approve.setM_name(m_name);
			
			ApproveDAO dao = ApproveDAO.getInstance();
			dao.writeApprove(approve);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
