package com.hgs.approve.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.approve.model.ApproveDAO;
import com.hgs.approve.model.ApprovePageDTO;
import com.hgs.approve.model.ApproveVO;

public class AdminApprovalService implements AService {
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
			String strpage = request.getParameter("page");
			int page;
			if(strpage == null)
				page = 1;
			else
				page = Integer.parseInt(strpage);
				if (page == 0)
					page = 1;
		
			// 전체 결재 요청 기록 불러오기
			List<ApproveVO> approveAllList = new ArrayList<ApproveVO>();
			ApproveDAO aDao = ApproveDAO.getInstance();
			approveAllList = aDao.getAllApprove((page-1)*10);
			int total = aDao.getAllTotalCount();
			ApprovePageDTO approveAllDTO = new ApprovePageDTO(total, page, approveAllList);
			
			session.setAttribute("approveAllDTO", approveAllDTO);
		}
	}
}
