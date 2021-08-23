package com.yjw.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjw.board.model.BoardVO;
import com.yjw.board.model.BoardDAO;

public class BoardCreateService implements InterBoardService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 세션 처리
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
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			String b_title = request.getParameter("title");
			String b_content = request.getParameter("content");
			String m_id = request.getParameter("id");
			
			BoardVO board = new BoardVO();
			board.setB_title(b_title);
			board.setB_content(b_content);
			board.setM_id(m_id);
			
			BoardDAO dao = BoardDAO.getInstance();
			int resultCode = dao.writeBoard(board);
			
			if(resultCode == 1) {
				System.out.println("DB에 글이 잘 입력되었습니다.");
			} else if(resultCode == 0) {
				System.out.println("에러 발생으로 글이 입력되지 않았습니다");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
