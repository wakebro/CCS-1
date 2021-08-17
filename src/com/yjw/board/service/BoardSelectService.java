package com.yjw.board.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjw.board.model.BoardDAO;
import com.yjw.board.model.BoardVO;

public class BoardSelectService implements InterBoardService{
	
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
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardVO> boardList = dao.getBoardList();
		
		System.out.println(boardList);
		session.setAttribute("boardList", boardList);
		
	}

}
