package com.hgs;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hgs.user.service.*;
import com.hgs.board.service.*;
import com.hgs.commute.service.*;
import com.hgs.commute.service.WriteCommuteService;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//PrintWriter out = response.getWriter();
		
		String uri = request.getRequestURI();
		System.out.println("URI 패턴 : " + uri);
		
		UService uService = null;
		IBoardService bService = null;
		CService cService = null;
		String url = "";
		// 로그인
		if(uri.equals("/ccs/login_proc.do")) {
			uService = new LoginService();
			uService.execute(request, response);
			url = "/main.do?page=1";
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
		// 메인화면
		else if (uri.equals("/ccs/main.do")) {
			uService = new MainPageService();
			uService.execute(request, response);
			url = "main.jsp";
		}
		// 게시판
		else if (uri.contentEquals("/ccs/board.do")) {
			bService = new BoardPagingService();
			bService.execute(request, response);
			url = "/board/board_list.jsp";
		}
		// 게시판 글 작성
		else if (uri.contentEquals("/ccs/write.do")) {
			url = "/board/board_write_form.jsp";
		}
		// 게시판 글 작성처리
		else if (uri.contentEquals("/ccs/write_proc.do")) {
			bService = new BoardWriteService();
			bService.execute(request, response);
			url = "board.do";
		}
		// 게시판 글 자세히
		else if (uri.contentEquals("/ccs/boarddetail.do")) {
			bService = new BoardDetailService();
			bService.execute(request, response);
			url = "/board/board_detail.jsp";
		}
		// 게시판 글 수정_폼
		else if(uri.equals("/ccs/boardupdateform.do")) {
			bService = new BoardDetailService();
			bService.execute(request, response);
			url = "/board/board_update_form.jsp";
		}
		// 게시판 글 수정
		else if(uri.equals("/ccs/boardupdate.do")) {
			bService = new BoardUpdateService();
			bService.execute(request, response);
			String strBid = request.getParameter("b_no");
			url = "boarddetail.do?b_no="+strBid;
		}
		// 게시판 글 삭제
		else if(uri.equals("/ccs/boarddelete.do")) {
			bService = new BoardDeleteService();
			bService.execute(request, response);
			url = "board.do";
		}
		// 사원 리스트
		else if(uri.equals("/ccs/member.do")) {
			uService = new MemberPagingService();
			uService.execute(request, response);
			url = "member_list.jsp";
		}
		// 출/퇴근
		else if(uri.equals("/ccs/commute.do")) {
			cService = new WriteCommuteService();
			cService.execute(request, response);
			url = "/main.do?page=1";
		}
		else {
			url = "login.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
