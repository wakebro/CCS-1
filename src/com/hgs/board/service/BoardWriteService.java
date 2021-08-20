package com.hgs.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgs.board.model.*;;

// 구현클래스이기 때문에 implements를 실행
public class BoardWriteService implements IBoardService{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String idSession = "";
		idSession = (String)session.getAttribute("session_id");
		
		if(idSession == null || idSession.equals("")) {
			try {
				RequestDispatcher dp = request.getRequestDispatcher("login.jsp");
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// getParameter로 폼에서 받아온 데이터를 변수에 저장
			try {
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=utf-8");
				String bTitle = request.getParameter("b_title");
				String bContent = request.getParameter("b_content");
				String mid = request.getParameter("m_id");
				
				BoardVO board = new BoardVO();
				board.setb_title(bTitle);
				board.setb_content(bContent);
				board.setm_id(mid);
				
				BoardDAO dao = BoardDAO.getInstance();
				int resultCode = dao.write(board);
				
				if(resultCode == 1) {
					System.out.println("DB에 글이 잘 입력되었습니다.");
				} else if(resultCode == 0) {
					System.out.println("에러발생으로 글이 입력되지 않았습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
