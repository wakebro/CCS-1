package com.wys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wys.model.InOutDAO;
import com.wys.model.InOutVO;

public class SelectClockInService implements InOutService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");

			InOutDAO dao = InOutDAO.getInstance();

			InOutVO inOutVO = new InOutVO();
			
			//나중에 세션 넣기!!
			inOutVO.setM_no(1);
			
			inOutVO = dao.selectClockIn(inOutVO);
			
			System.out.println(inOutVO.getClock_in_time());
			
			
			request.setAttribute("inOutVO", inOutVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}
	
}

