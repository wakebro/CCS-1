package com.yjw.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjw.board.model.BoardDAO;

public class BoardDeleteService implements InterBoardService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 세션 검사
		HttpSession session = request.getSession();
		String idSession = (String)session.getAttribute("session_id");

		if(idSession == null) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String b_no = request.getParameter("b_no");
			
			BoardDAO dao = BoardDAO.getInstance();
			int resultCode = dao.deleteBoard(b_no);
			
			if(resultCode == 1) {
				System.out.println("게시글이 정상적으로 삭제되었습니다.");
			} else if(resultCode == 0) {
				System.out.println("에러 발생으로 인해 게시글이 삭제되지 않았습니다.");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
