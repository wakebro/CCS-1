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
		String idSession = (String)session.getAttribute("session_id");
		
		if(idSession != null) {
		
			BoardDAO dao = BoardDAO.getInstance();
			BoardVO board = new BoardVO();
			
			board.setb_no((int)Integer.parseInt(request.getParameter("b_no")));
			board.setb_title(request.getParameter("b_title"));
			board.setb_content(request.getParameter("b_content"));
			
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
