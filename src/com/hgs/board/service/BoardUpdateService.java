package com.hgs.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.board.model.*;

public class BoardUpdateService implements IBoardService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String idSession = (String)session.getAttribute("sid");
		
		if(idSession != null) {
		
			// DAO와 VO 생성
			BoardDAO dao = BoardDAO.getInstance();
			BoardVO board = new BoardVO();
			
			// VO setter로 저장
			board.setbId((int)Integer.parseInt(request.getParameter("bId")));
			board.setbTitle(request.getParameter("bTitle"));
			board.setbContent(request.getParameter("bContent"));
			
			// DAO의 update 호출
			int resultcode = dao.update(board);
			if (resultcode == 1) {
				System.out.println("수정 성공");
			}else if(resultcode == 0) {
				System.out.println("수정 실패");
			}
		} else {
			try {
				RequestDispatcher dp = request.getRequestDispatcher("/04-user/user_login_form.jsp");
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
