package com.hgs.board.service;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.board.model.*;

public class BoardListService implements IBoardService{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String idSession = "";
		idSession = (String)session.getAttribute("session_id");
		
		if(idSession == null || idSession.equals("")) {
			try {
				RequestDispatcher dp = request.getRequestDispatcher("/04-user/user_login_form.jsp");
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				
				BoardDAO dao = BoardDAO.getInstance();
				
				ArrayList<BoardVO> boardList =  dao.getBoardList();
				
				request.setAttribute("boardList", boardList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
