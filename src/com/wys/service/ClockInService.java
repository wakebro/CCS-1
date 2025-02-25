package com.wys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberVO;
import com.wys.model.InOutDAO;
import com.wys.model.InOutVO;

public class ClockInService implements InOutService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();

			MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
			InOutDAO dao = InOutDAO.getInstance();
			InOutVO inOut = new InOutVO();
			
			inOut.setM_no(userInfo.getM_No());  // 세션이 들어오면 변경되야함
			inOut.setM_name(userInfo.getM_Name());
		
			dao.clockIn(inOut);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
