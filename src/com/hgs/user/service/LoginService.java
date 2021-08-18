package com.hgs.user.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.commute.model.CommuteDAO;
import com.hgs.commute.model.CommuteVO;
import com.hgs.user.model.UserDAO;
import com.hgs.user.model.UserVO;

public class LoginService implements UService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String id = request.getParameter("login_id");
		String pw = request.getParameter("login_pw");
		UserVO userInfo = null;

		if (id == null) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			UserDAO dao = UserDAO.getInstace();
			UserVO user = new UserVO();
			user.setId(id);
			user.setPw(pw);
			userInfo = dao.login(user);
			
			// 로그인
			if(userInfo.getId() == null) {
				try {
					session.invalidate();
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(userInfo.getDept_no()==1000) {
				session.setAttribute("admin", userInfo.getDept());
			}
			
			// 메인화면 출력 내용
			CommuteDAO cDao = CommuteDAO.getInstance();
			// 출퇴근 기록지
			List<CommuteVO> commuteList = cDao.bringDate(userInfo.getNo());
			// 최신 출퇴근 기록
			CommuteVO lastestDate = cDao.bringLastestDate(userInfo.getNo());

			// 세션 생성
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("session_id", id);
			session.setAttribute("commuteList", commuteList);
			session.setAttribute("lastestDate", lastestDate);
			
		}
	}
}
