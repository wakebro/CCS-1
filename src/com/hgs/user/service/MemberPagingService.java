package com.hgs.user.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.user.model.UserDAO;
import com.hgs.user.model.UserPageDTO;
import com.hgs.user.model.UserVO;

public class MemberPagingService implements UService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("session_id") == null || session.getAttribute("session_id").equals("")
				|| session.getAttribute("dept") == null) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {		
			String strPage = request.getParameter("page");
			int page;
			if (strPage == null) {
				page = 1;
			} else {
				page = Integer.parseInt(strPage);
				if (page == 0) {
					page = 1;
				}
			}
			UserDAO dao = UserDAO.getInstace();
			List<UserVO> userList = dao.getUserPageList((page - 1) * 10);
			int total = dao.getUserCount();
			
			UserPageDTO userPage = new UserPageDTO(total, page, userList); 
			
			request.setAttribute("userPage", userPage);
		}
	}
}
