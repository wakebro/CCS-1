package com.hgs.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hgs.board.model.*;

public class BoardPagingService implements IBoardService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// page 파라미터에 값을 가져오기
		String strPage = request.getParameter("page");
		int page;
		if (strPage == null) {
			page = 1;
		} else {
			page = Integer.parseInt(strPage);
			if (page == 0) {
				page = 1;
			}
		}
		// DAO 생성
		BoardDAO dao = BoardDAO.getInstance();

		// 현재 페이지의 전체 글 가져오기
		List<BoardVO> boardList = dao.getPageList((page - 1) * 10);
		int total = dao.getBoardCount();
		BoardPageDTO pageDTO = new BoardPageDTO(total, page, boardList); 
		
		request.setAttribute("pageDTO", pageDTO);
	}
}
