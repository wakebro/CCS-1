package com.hgs.user.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.commute.model.CommuteDAO;
import com.hgs.commute.model.CommutePageDTO;
import com.hgs.commute.model.CommuteVO;
import com.hgs.user.model.UserVO;

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
		} else {
			UserVO userInfo = (UserVO) session.getAttribute("userInfo");
			String strpage = request.getParameter("page");
			
			int page;
			if(strpage == null) {
				page = 1;
			}else {
				page = Integer.parseInt(strpage);
				if (page == 0)
					page = 1;
			}
			
			// 메인화면 출력 내용
			CommuteDAO dao = CommuteDAO.getInstance();
			
			// 전체 출퇴근 기록
			List<CommuteVO> commuteList = dao.bringDate(userInfo.getNo(), (page-1) * 5);
			
			// 출퇴근 기록 개수
			int total = dao.getCommuteCount(userInfo.getNo());
			CommutePageDTO commutePageDTO = new CommutePageDTO(total, page, commuteList); 

			// 최신 출퇴근 기록
			CommuteVO lastestDate = dao.bringLastestDate(userInfo.getNo());
			
			
			// 세션 생성
			session.setAttribute("commuteList", commuteList);
			session.setAttribute("lastestDate", lastestDate);
			request.setAttribute("commutePageDTO", commutePageDTO);
		}
	}
}
