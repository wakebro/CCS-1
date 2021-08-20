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

import com.hgs.user.service.CheckSessionService;
import com.hgs.user.service.JoinProcService;
import com.hgs.user.service.JoinService;
import com.hgs.user.service.LoginService;
import com.hgs.user.service.Logout;
import com.hgs.user.service.UService;
import com.hgs.user.service.UpdateService;

import com.yjw.board.service.BoardCreateService;
import com.yjw.board.service.BoardDeleteService;
import com.yjw.board.service.BoardDetailService;
import com.yjw.board.service.BoardPagingService;
import com.yjw.board.service.BoardSearchService;
import com.yjw.board.service.BoardUpdateService;
import com.yjw.board.service.BoardViewService;
import com.yjw.board.service.InterBoardService;

/**
 * Servlet implementation class PatternServlet.
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
		System.out.println("URI 패턴 : " + uri);
		// 로직 실행 후에 넘어갈 경로 지정 역할
		String url = null;
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// jsp페이지가 html형식으로 이뤄져 있음을 알려주는 코드
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
// ---------------------------------------------------------------------
		
		UService uService = null;
		
		
		// 로그인
			if(uri.equals("/ccs/login_proc.do")) {
				uService = new LoginService();
				uService.execute(request, response);
				url = "main.jsp";
			} 
			// 로그아웃
			else if (uri.equals("/ccs/logout.do")) {
				uService = new Logout();
				uService.execute(request, response);
				url = "login.jsp";
			}
			// 회원가입 창
			else if (uri.equals("/ccs/join.do")) {
				uService = new JoinService();
				uService.execute(request, response);
				url = "join.jsp";
			}
			// 회원가입
			else if (uri.equals("/ccs/join_proc.do")) {
				uService = new JoinProcService();
				uService.execute(request, response);
				url = "login.jsp";
			}
			// 회원정보 창
			else if (uri.equals("/ccs/userinfo.do")) {
				uService = new CheckSessionService();
				uService.execute(request, response);
				url = "info.jsp";
			}
			// 회원정보 수정창
			else if (uri.equals("/ccs/update.do")) {
				uService = new CheckSessionService();
				uService.execute(request, response);
				url = "update.jsp";
			}
			// 회원정보 수정
			else if (uri.equals("/ccs/update_proc.do")) {
				uService = new UpdateService();
				uService.execute(request, response);
				url = "info.jsp";
			}
		
// ---------------------------------------------------------------------
		
		// 게시판 글쓰기
		else if(uri.equals("/ccs/boardCreate.do")) {
			System.out.println("글쓰기를 요청합니다.");
			ibs = new BoardCreateService();
			ibs.execute(request, response);
			url = "/board.do";
		} 
		// 게시판 목록 조회
		else if(uri.equals("/ccs/board.do")) {
			System.out.println("게시판 페이지로 이동합니다.");
			ibs = new BoardPagingService();
			ibs.execute(request, response);
			url = "/board/board_list_up.jsp";
		} 
		// 게시판 목록 검색
		else if(uri.equals("/ccs/boardSearch.do")) {
			System.out.println("게시판 검색 페이지로 이동합니다.");
			ibs = new BoardSearchService();
			ibs.execute(request, response);
			url = "/board/board_list_up.jsp";
		} 
		// 게시판 목록 조회순
		else if(uri.equals("/ccs/boardView.do")) {
			System.out.println("게시판 조회순 페이지로 이동합니다.");
			ibs = new BoardViewService();
			ibs.execute(request, response);
			url = "/board/board_list_up.jsp";
		} 
		// 게시판 글 조회
		else if(uri.equals("/ccs/boardDetail.do")) {
			System.out.println("글 조회 페이지로 이동합니다.");
			ibs = new BoardDetailService();
			ibs.execute(request, response);
			url="/board/board_detail.jsp";
		} 
		// 게시판 수정 페이지 열기
		else if(uri.equals("/ccs/boardUpdate.do")) {
			System.out.println("글 수정 페이지로 이동합니다.");
			ibs = new BoardDetailService();
			ibs.execute(request, response);
			url="/board/board_update.jsp";
		} 
		// 게시판 수정 확인
		else if(uri.equals("/ccs/boardUpdateOK.do")) {
			System.out.println("글 수정을 요청합니다.");
			ibs = new BoardUpdateService();
			ibs.execute(request, response);
			url="/board/board_detail.jsp";
		} 
		// 게시판 삭제
		else if(uri.equals("/ccs/boardDelete.do")) {
			System.out.println("글 삭제 페이지로 이동합니다.");
			ibs = new BoardDeleteService();
			ibs.execute(request, response);
			url="/board.do";
		} 
		else {
			out.print("잘못된 패턴입니다 ...");
		}
		
		// 포워딩 로직
		// 컨트롤러에서 출력에 필요한 데이터를 저장했다가 포워드로 jsp파일에 전달
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}
}
