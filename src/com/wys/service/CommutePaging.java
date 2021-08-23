package com.wys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wys.model.InOutDAO;
import com.wys.model.InOutDTO;
import com.wys.model.InOutVO;

public class CommutePaging implements InOutService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String strPage = request.getParameter("page");
		int page = 1;
		if(strPage != null) {
			page = Integer.parseInt(strPage);
		}
		// DAO생성
		InOutDAO dao = InOutDAO.getInstance();
		
		// 현재 보고 있는 페이지의 전체 글 가져오기
		// 페이지 번호를 그냥 넘기지 않고, 시작 번호를 계산해서 넘김.
		List<InOutVO> commuteList = dao.getPageList((page - 1) * 5);
		// 얻어온 글 전체 개수와 현재 조회중인 페이지 정보를 DTO에 넘겨줌.
		int count = dao.getCommuteListCount();
		
		// DTO의 역할은 페이지 하단에 링크만들 정보를 계산하는 것
		InOutDTO pageDTO = new InOutDTO(count, page, commuteList);
		
		System.out.println("링크 버튼 정보 :: " + pageDTO);
		
		// 포워딩 하기 위해 적재하기
		request.setAttribute("commuteList", commuteList);
		request.setAttribute("pageDTO", pageDTO);
	
	}

	
}
