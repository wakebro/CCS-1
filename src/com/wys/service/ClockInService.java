package com.wys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberVO;
=======

>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
import com.wys.model.InOutDAO;
import com.wys.model.InOutVO;

public class ClockInService implements InOutService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
<<<<<<< HEAD
			HttpSession session = request.getSession();

			MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
			InOutDAO dao = InOutDAO.getInstance();
			InOutVO inOut = new InOutVO();
			
			inOut.setM_no(userInfo.getM_No());  // 세션이 들어오면 변경되야함
			inOut.setM_name(userInfo.getM_Name());
=======

			InOutDAO dao = InOutDAO.getInstance();

			InOutVO inOut = new InOutVO();
			
			inOut.setM_no(1000);  // 세션이 들어오면 변경되야함
			inOut.setM_name("원예슬");
>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
		
			dao.clockIn(inOut);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
