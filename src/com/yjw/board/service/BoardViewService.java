package com.yjw.board.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjw.board.model.BoardDAO;
import com.yjw.board.model.BoardPageDTO;
import com.yjw.board.model.BoardVO;

public class BoardViewService implements InterBoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 세션 검사
		HttpSession session = request.getSession();
		String idSession = (String)session.getAttribute("session_id");

		if(idSession == null) {
			try {
<<<<<<< HEAD
				RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
=======
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
				rd.forward(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		String strPage = request.getParameter("page");
		int page = 1;
		if(strPage != null) {
			page = Integer.parseInt(strPage);
		}
		
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardVO> boardList = dao.getViewPage((page - 1) * 10);
		System.out.println("페이지 글들 : " + boardList);
		
		int totalNum = dao.getBoardTotal();
		BoardPageDTO pageDTO = new BoardPageDTO(totalNum, page, boardList); 
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageDTO", pageDTO);
		request.setAttribute("viewTotal", totalNum);
	}
}
