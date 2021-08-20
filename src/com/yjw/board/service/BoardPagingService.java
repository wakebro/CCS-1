package com.yjw.board.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjw.board.model.BoardDAO;
import com.yjw.board.model.BoardPageDTO;
import com.yjw.board.model.BoardVO;

public class BoardPagingService implements InterBoardService{

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

		String strPage = request.getParameter("page");
		// 파라미터 값이 없을 때는 1페이지가 기본
		int page = 1;
		if(strPage != null) {
			page = Integer.parseInt(strPage);
		}
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 현재 페이지의 글 모두 가져오기 
		List<BoardVO> boardList = dao.getBoardList((page - 1) * 10);
		System.out.println("페이지 글들 : " + boardList);
		
		int totalNum = dao.getBoardTotal();
		BoardPageDTO pageDTO = new BoardPageDTO(totalNum, page, boardList);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageDTO", pageDTO);
	}
}
