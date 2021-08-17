package com.hgs.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.board.model.*;

public class BoardDetailService implements IBoardService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String idSession = (String)session.getAttribute("session_id");
		
		if(idSession != null) {
			try {
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
	
				String b_no = request.getParameter("b_no");
				System.out.println(b_no);
				
				BoardDAO dao = BoardDAO.getInstance();
				
				// DAO에 글 번호를 넘겨 글 조회수 상승
//				dao.upHit(b_title);
				
				BoardVO content = dao.getBoardDetail(b_no);
				request.setAttribute("content", content);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				RequestDispatcher dp = request.getRequestDispatcher("login.jsp");
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
