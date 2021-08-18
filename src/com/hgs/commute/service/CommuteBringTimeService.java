package com.hgs.commute.service;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hgs.commute.model.CommuteDAO;
import com.hgs.commute.model.CommuteVO;

public class CommuteBringTimeService implements CService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int m_no = Integer.parseInt(request.getParameter("cKeyword"));
		
		CommuteDAO dao = CommuteDAO.getInstance();
		// 가장 최근 출퇴근의 기록 가져오기
		CommuteVO DBdate = new CommuteVO(); 
		DBdate = dao.bringDate(m_no);
		
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		// DB출근 기록이 오늘 날자와 같은지 확인
//		String db_atten = date.format(DBdate.getAttendance());
//		String today = date.format(new Date());
		
		// 출퇴근 기록이 모두 있다면 새로 출근
		if(DBdate.getAttendance() != null && DBdate.getLeave_work() != null) {
			dao.write_work(m_no);
			DBdate = dao.bringDate(m_no);
		}
		// 출근 기록이 있지만 퇴근 기록이 없는 경우 퇴근
		else {
			dao.write_leave(DBdate.getC_no());
		}
	}
}
