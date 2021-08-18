package com.yjw.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjw.board.model.BoardDAO;
import com.yjw.board.model.BoardVO;

public class BoardDetailService implements InterBoardService{
	public void execute (HttpServletRequest request, HttpServletResponse response) {
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
		
		String b_no = request.getParameter("b_no");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 조회수 1씩 증가
		dao.upView(b_no);
		
		BoardVO board = dao.getBoardDetail(b_no);
		System.out.println(board); // 디버깅
		
		if(idSession.equals(board.getM_id())) {
			System.out.println(1);
			request.setAttribute("writer", 1);
		} else {
			System.out.println(0);
			request.setAttribute("writer", 0);
		}
		
		request.setAttribute("board", board);
	}
}
