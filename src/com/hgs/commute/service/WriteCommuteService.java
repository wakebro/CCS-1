package com.hgs.commute.service;

import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.commute.model.CommuteDAO;
import com.hgs.commute.model.CommutePageDTO;
import com.hgs.commute.model.CommuteVO;
import com.hgs.user.model.UserVO;

public class WriteCommuteService implements CService {
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
			int m_no = Integer.parseInt(request.getParameter("cKeyword"));
			String strpage = request.getParameter("page");
			
			int page;
			if(strpage == null) {
				page = 1;
			}else {
				page = Integer.parseInt(strpage);
				if (page == 0)
					page = 1;
			}
			CommuteVO lastestDate =(CommuteVO) session.getAttribute("lastestDate");
			CommuteDAO dao = CommuteDAO.getInstance();
			UserVO userInfo = (UserVO) session.getAttribute("userInfo");
			
			// 새 출근인 경우
			if(lastestDate == null) {
				dao.write_work(m_no);
			}
			// 출퇴근 기록이 모두 있다면 새로 출근
			else if(lastestDate.getAttendance() != null && lastestDate.getWork_leave() != null) {
				dao.write_work(m_no);
			}
			// 출근 기록이 있지만 퇴근 기록이 없는 경우 퇴근
			else {
				dao.write_leave(lastestDate.getC_no());
			}
			
			// 출퇴근 정보 최신화
			lastestDate = dao.bringLastestDate(m_no);
			List<CommuteVO> commuteList = dao.bringDate(m_no, page);

			int total = dao.getCommuteCount(userInfo.getNo());
			System.out.println(total);
			CommutePageDTO commutePageDTO = new CommutePageDTO(total, page, commuteList); 

			session.setAttribute("commuteList", commuteList);
			session.setAttribute("lastestDate", lastestDate);
			request.setAttribute("commutePageDTO", commutePageDTO);
		}
	}
}
