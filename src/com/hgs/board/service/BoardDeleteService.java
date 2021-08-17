package com.hgs.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.board.model.*;

public class BoardDeleteService implements IBoardService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String idSession = (String)session.getAttribute("sid");
		
		if(idSession != null) {
			String bId = request.getParameter("bId");
			BoardDAO dao = BoardDAO.getInstance();
			
			int resultcode = dao.delete(bId);
			if (resultcode == 1) {
				System.out.println("글 삭제 성공");
			} else if(resultcode == 0) {
				System.out.println("글 삭제 실패");
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
