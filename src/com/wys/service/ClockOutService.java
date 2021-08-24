package com.wys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberVO;
import com.wys.model.InOutDAO;
import com.wys.model.InOutVO;

public class ClockOutService implements InOutService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 퇴근시간만 업데이트 되면 됨
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();

			InOutVO inOut = new InOutVO();
			MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
			
			inOut.setM_no(userInfo.getM_No());  // 세션이 받아오면 변경해줘야 함
			InOutDAO dao = InOutDAO.getInstance();
			dao.clockOut(inOut);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
