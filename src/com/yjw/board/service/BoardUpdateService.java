package com.yjw.board.service;

import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjw.board.model.BoardDAO;
import com.yjw.board.model.BoardVO;

public class BoardUpdateService implements InterBoardService{
	
	public void execute (HttpServletRequest request, HttpServletResponse response) {
		// 세션 검사
		HttpSession session = request.getSession();
		String idSession = (String)session.getAttribute("session_id");

		if(idSession == null) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
				rd.forward(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String strB_no = request.getParameter("textNum");
			int b_no = Integer.parseInt(strB_no);
			String strB_view = request.getParameter("view");
			int b_view = Integer.parseInt(strB_view);

			String b_title = request.getParameter("title");
			String b_content = request.getParameter("content");
			String m_id = request.getParameter("writer");
			String strB_date = request.getParameter("date");
			Timestamp b_date = Timestamp.valueOf(strB_date);
			
			BoardVO board = new BoardVO();
			board.setB_no(b_no);
			board.setB_view(b_view);
			board.setB_title(b_title);
			board.setB_content(b_content);
			board.setM_id(m_id);
			board.setB_date(b_date);
			
			BoardDAO dao = BoardDAO.getInstance();
			int resultCode = dao.updateBoard(board);
			if(resultCode == 1) {
				System.out.println("게시글이 정상적으로 수정되었습니다.");
			} else if(resultCode == 0) {
				System.out.println("에러 발생으로 인해 게시글이 수정되지 않았습니다.");
			}
			
			// 수정 후 글 조회 페이지를 열때 필요한 데이터 호출 
			String stringB_no = String.valueOf(b_no);
			BoardVO board2 = dao.getBoardDetail(stringB_no);
			request.setAttribute("board", board2);
			
			// 자신이 올린 게시물만 수정, 삭제할 수 있게 하는 로직
			// 세션에 저장된 아이디와 게시판의 글쓴이가 일치하는지 검사
			if(idSession.equals(board.getM_id())) {
				System.out.println(1);
				request.setAttribute("writer", 1);
			} else {
				System.out.println(0);
				request.setAttribute("writer", 0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
