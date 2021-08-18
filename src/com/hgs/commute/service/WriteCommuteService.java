package com.hgs.commute.service;

import java.util.List;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.commute.model.CommuteDAO;
import com.hgs.commute.model.CommuteVO;

public class WriteCommuteService implements CService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int m_no = Integer.parseInt(request.getParameter("cKeyword"));
		HttpSession session = request.getSession();
		
		CommuteVO lastestDate =(CommuteVO) session.getAttribute("lastestDate");
		CommuteDAO dao = CommuteDAO.getInstance();
		
		
		// 출퇴근 기록이 모두 있다면 새로 출근
		if(lastestDate.getAttendance() != null && lastestDate.getLeave_work() != null) {
			dao.write_work(m_no);
			lastestDate = dao.bringLastestDate(m_no);
			List<CommuteVO> commuteList = dao.bringDate(m_no);
			session.setAttribute("commuteList", commuteList);
			session.setAttribute("lastestDate", lastestDate);
		}
		// 출근 기록이 있지만 퇴근 기록이 없는 경우 퇴근
		else {
			dao.write_leave(lastestDate.getC_no());
			lastestDate = dao.bringLastestDate(m_no);
			List<CommuteVO> commuteList = dao.bringDate(m_no);
			session.setAttribute("commuteList", commuteList);
			session.setAttribute("lastestDate", lastestDate);
		}
	}
}
