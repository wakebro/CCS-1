package com.hgs.approve.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.approve.model.*;
import com.hgs.user.model.UserVO;

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
			UserVO userInfo = (UserVO) session.getAttribute("userInfo");
			String strpage = request.getParameter("page");
			int page;
			if(strpage == null)
				page = 1;
			else
				page = Integer.parseInt(strpage);
				if (page == 0)
					page = 1;
			
			// 결재 종류 불러오기
			List<A_CategoryVO> categoryList = new ArrayList<A_CategoryVO>();
			A_CategoryDAO dao = A_CategoryDAO.getInstance();
			categoryList = dao.getCategory();
			session.setAttribute("categoryList", categoryList);
			
			// 내 결재 요청 기록 불러오기
			List<ApproveVO> approveList = new ArrayList<ApproveVO>();
			ApproveDAO aDao = ApproveDAO.getInstance();
			approveList = aDao.getMyApprove(userInfo.getNo(), (page-1)*10);
			int total = aDao.getMyTotalCount(userInfo.getNo());
			ApprovePageDTO approveDTO = new ApprovePageDTO(total, page, approveList);
			
			session.setAttribute("approveDTO", approveDTO);
		}
	}
}
