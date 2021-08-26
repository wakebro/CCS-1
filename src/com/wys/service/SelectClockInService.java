package com.wys.service;

<<<<<<< HEAD
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberVO;
import com.wys.model.InOutDAO;
import com.wys.model.InOutDTO;
=======
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wys.model.InOutDAO;
>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
import com.wys.model.InOutVO;

public class SelectClockInService implements InOutService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
<<<<<<< HEAD
		HttpSession session = request.getSession();
		session = request.getSession();
		
		if(session.getAttribute("session_id") == null || session.getAttribute("session_id").equals("")) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				request.setCharacterEncoding("UTF-8");
				String strPage = request.getParameter("page");
				int page = 1;
				if(strPage != null) {
					page = Integer.parseInt(strPage);
				}
	
				MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
				InOutDAO dao = InOutDAO.getInstance();
				InOutVO inOutVO = new InOutVO();
				
				inOutVO.setM_no(userInfo.getM_No());
				inOutVO = dao.selectClockIn(inOutVO);
				
				System.out.println(inOutVO.getClock_in_time());
				
				// 출퇴근 목록 조회
				List<InOutVO> commuteList = dao.getCommuteList(inOutVO, (page - 1) * 5);
				int commuteListCount = dao.getCommuteListCount();
				
				InOutDTO inOutDTO = new InOutDTO(commuteListCount, page, commuteList);
				
				// view단(jsp) 에 값 넣는곳
				// 오늘 출퇴근 한 기록
				request.setAttribute("inOutVO", inOutVO);
				// 내가 출퇴근한 모든 기록
				request.setAttribute("commuteList", commuteList);
				request.setAttribute("commuteListCount", commuteListCount);
				request.setAttribute("inOutDTO", inOutDTO);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
=======
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
	
>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
}

