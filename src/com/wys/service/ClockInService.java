package com.wys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wys.model.InOutDAO;
import com.wys.model.InOutVO;

public class ClockInService implements InOutService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");

			InOutDAO dao = InOutDAO.getInstance();

			InOutVO inOut = new InOutVO();
			
			inOut.setM_no(1000);  // 세션이 들어오면 변경되야함
			inOut.setM_name("원예슬");
		
			dao.clockIn(inOut);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
