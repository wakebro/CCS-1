package com.yjw;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjw.board.service.InterBoardService;

/**
 * Servlet implementation class PatternServlet
 */
@WebServlet("*.do")
public class PatternServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PatternServlet() {
        super();
        System.out.println("확장자 패턴 생성");
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("확장자 패턴 서버 연결");
	}

	public void destroy() {
		System.out.println("확장자 패턴 소멸");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	// get, post로 구분지어서 처리하고 싶지 않음
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InterBoardService ibs = null;
		
		// 확장자 패턴에서 주소 값을 가져와주는 역할
		String uri = request.getRequestURI();
		// 로직 실행 후에 넘어갈 경로 지정 역할
		String ui = null;
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// jsp페이지가 html형식으로 이뤄져 있음을 알려주는 코드
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		if(uri.equals("/ccs/boardCreate.do")) {
			
			System.out.println("글쓰기 페이지으로 이동합니다.");
		
		} else if(uri.equals("/ccs/boardSelect.do")) {
			
			System.out.println("게시판 페이지로 이동합니다.");
			
		} else if(uri.equals("/ccs/boardDetail.do")) {
		
			System.out.println("글 조회 페이지로 이동합니다.");
			
		} else if(uri.equals("/ccs/boardUpdate.do")) {
			
			System.out.println("글 수정 페이지로 이동합니다.");
			
		} else if(uri.equals("/ccs/boardUpdateOK.do")) {

			
		} else if(uri.equals("/ccs/boardDelete.do")) {
			
			System.out.println("글 삭제 페이지로 이동합니다.");
			
		} else {
			out.print("잘못된 패턴입니다 ...");
		}
		
		// 포워딩 로직
		// 컨트롤러에서 출력에 필요한 데이터를 저장했다가 포워드로 jsp파일에 전달
		RequestDispatcher dp = request.getRequestDispatcher(ui);
		dp.forward(request, response);
		
	}
}
