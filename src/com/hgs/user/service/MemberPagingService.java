package com.hgs.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hgs.user.model.UserDAO;
import com.hgs.user.model.UserPageDTO;
import com.hgs.user.model.UserVO;

public class MemberPagingService implements UService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
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
