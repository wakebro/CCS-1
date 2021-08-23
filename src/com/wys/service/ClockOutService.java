package com.wys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wys.model.InOutDAO;
import com.wys.model.InOutVO;

public class ClockOutService implements InOutService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 퇴근시간만 업데이트 되면 됨
			request.setCharacterEncoding("UTF-8");
			InOutVO inOut = new InOutVO();
			
			inOut.setM_no(1000);  // 세션이 받아오면 변경해줘야 함

			InOutDAO dao = InOutDAO.getInstance();
			dao.clockOut(inOut);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
