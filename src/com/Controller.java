package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hgs.approve.service.AService;
import com.hgs.approve.service.AdminApprovalService;
import com.hgs.approve.service.AdminApproveConfirmService;
import com.hgs.approve.service.AdminApproveDetail;
import com.hgs.approve.service.Approve;
import com.hgs.approve.service.ApproveWriteService;
import com.hgs.user.service.CheckAdminSessionService;
import com.hgs.user.service.CheckSessionService;
import com.hgs.user.service.JoinService;
import com.hgs.user.service.MemberPagingService;
import com.hgs.user.service.UService;
import com.hgs.user.service.UpdateService;
import com.sjh.service.IMemberService;
import com.sjh.service.MemberJoinService;
import com.sjh.service.MemberLoginService;
import com.sjh.service.MemberLogoutService;
import com.wys.service.ClockInService;
import com.wys.service.ClockOutService;
import com.wys.service.InOutService;
import com.wys.service.SelectClockInService;
import com.yjw.board.service.BoardCreateService;
import com.yjw.board.service.BoardDeleteService;
import com.yjw.board.service.BoardDetailService;
import com.yjw.board.service.BoardPagingService;
import com.yjw.board.service.BoardSearchService;
import com.yjw.board.service.BoardUpdateService;
import com.yjw.board.service.BoardViewService;
import com.yjw.board.service.InterBoardService;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// jsp페이지가 html형식으로 이뤄져 있음을 알려주는 코드
		response.setContentType("text/html; charset=UTF-8");
		
		// 확장자 패턴에서 주소 값을 가져와주는 역할
		String uri = request.getRequestURI();
		System.out.println("URI 패턴 : " + uri);
		// 로직 실행 후에 넘어갈 경로 지정 역할
		String url = "";
		
		InterBoardService ibs = null;
		IMemberService sv = null;
		InOutService inOutService = null;
		UService uService = null;
		AService aService = null;
// ---------------------------------------------------------------------
		
		// login
		if(uri.equals("/ccs/memberjoin.do")) {
			sv = new MemberJoinService();
			sv.execute(request, response);
			url="/member/member_login_form.jsp";
		}else if(uri.equals("/ccs/login.do")) {
			sv = new MemberLoginService();
			sv.execute(request, response);
			url="commute.do";
		}else if(uri.equals("/ccs/logout.do")) {
			sv = new MemberLogoutService();
			sv.execute(request, response);
			url="/member/member_login_form.jsp";
		}
		else if (uri.equals("/ccs/update.do")) {
			uService = new CheckSessionService();
			uService.execute(request, response);
			url = "/member/update.jsp";
		}
		else if (uri.equals("/ccs/update_proc.do")) {
			uService = new UpdateService();
			uService.execute(request, response);
			url = "/member/info.jsp";
		}
// ---------------------------------------------------------------------	
		// commuite
		else if(uri.equals("/ccs/commute.do")) {
			inOutService = new SelectClockInService();
			inOutService.execute(request, response);
			url = "/inout/commute.jsp";
		}else if(uri.equals("/ccs/doCommute.do")) {
			String inTime = request.getParameter("inTime");
			System.out.println("inTime ::: " + inTime);
			if(inTime != null && !"".equals(inTime)) {
				// 퇴근
				inOutService = new ClockOutService();
				inOutService.execute(request, response);
				System.out.println("퇴근 요청");
				System.out.println("111");
				url = "/commute.do";
			} else {
				// 출근시 출근도장 확인 화면으로 이동
				inOutService = new ClockInService();
				inOutService.execute(request, response);
				System.out.println("출근 요청");
				url = "/commute.do";
			}
		}
// ---------------------------------------------------------------------
		// 회원가입 창
		else if (uri.equals("/ccs/join.do")) {
			uService = new JoinService();
			uService.execute(request, response);
			url = "/member/members_join_form.jsp";
		}
		// 회원정보 창
		else if (uri.equals("/ccs/userinfo.do")) {
			uService = new CheckSessionService();
			uService.execute(request, response);
			url = "/member/info.jsp";
		}
		// 결재창
		else if(uri.equals("/ccs/approval.do")) {
			aService = new Approve();
			aService.execute(request, response);
			url = "/approval/approval.jsp";
		}
		// 결재 요청
		else if(uri.equals("/ccs/approval_proc.do")) {
			aService = new ApproveWriteService();
			aService.execute(request, response);
			url = "approval.do?page=1";
		}
		// 관리자 창
		else if(uri.equals("/ccs/admin.do")) {
			uService = new CheckAdminSessionService();
			uService.execute(request, response);
			url = "/admin/admin.jsp";
		}
		// 관리자 전용 사원 리스트
		else if(uri.equals("/ccs/member.do")) {
			uService = new MemberPagingService();
			uService.execute(request, response);
			url = "/admin/member_list.jsp";
		}
		// 관리자 전용 결제 서류
		else if(uri.equals("/ccs/adminapproval.do")) {
			aService = new AdminApprovalService();
			aService.execute(request, response);
			url = "/admin/approval.jsp";
		}
		// 관리자 전용 결제 디테일
		else if(uri.equals("/ccs/adminapprodetail.do")) {
			aService = new AdminApproveDetail();
			aService.execute(request, response);
			url = "/admin/approval_detail.jsp";
		}
		// 관리자 전용 결제 처리
		else if(uri.equals("/ccs/adminapproconfirm.do")) {
			aService = new AdminApproveConfirmService();
			aService.execute(request, response);
			url = "/adminapproval.do?page=1";
		}		
// ---------------------------------------------------------------------
		// 게시판 글쓰기
		else if(uri.equals("/ccs/boardCreate.do")) {
			ibs = new BoardCreateService();
			ibs.execute(request, response);
			url = "/board.do";
		} 
		// 게시판 목록 조회
		else if(uri.equals("/ccs/board.do")) {
			ibs = new BoardPagingService();
			ibs.execute(request, response);
			url = "/board/board_list_up.jsp";
		} 
		// 게시판 목록 검색
		else if(uri.equals("/ccs/boardSearch.do")) {
			ibs = new BoardSearchService();
			ibs.execute(request, response);
			url = "/board/board_list_up.jsp";
		} 
		// 게시판 목록 조회순
		else if(uri.equals("/ccs/boardView.do")) {
			ibs = new BoardViewService();
			ibs.execute(request, response);
			url = "/board/board_list_up.jsp";
		} 
		// 게시판 글 조회
		else if(uri.equals("/ccs/boardDetail.do")) {
			ibs = new BoardDetailService();
			ibs.execute(request, response);
			url="/board/board_detail.jsp";
		} 
		// 게시판 수정 페이지 열기
		else if(uri.equals("/ccs/boardUpdate.do")) {
			ibs = new BoardDetailService();
			ibs.execute(request, response);
			url="/board/board_update.jsp";
		} 
		// 게시판 수정 확인
		else if(uri.equals("/ccs/boardUpdateOK.do")) {
			ibs = new BoardUpdateService();
			ibs.execute(request, response);
			url="/board/board_detail.jsp";
		} 
		// 게시판 삭제
		else if(uri.equals("/ccs/boardDelete.do")) {
			ibs = new BoardDeleteService();
			ibs.execute(request, response);
			url="/board.do";
		} 
		else {
			url="/board.do";
		}
		// 포워딩 로직
		// 컨트롤러에서 출력에 필요한 데이터를 저장했다가 포워드로 jsp파일에 전달
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		
	}
}
