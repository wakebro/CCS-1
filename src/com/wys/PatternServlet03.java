package com.wys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wys.model.InOutVO;
import com.wys.service.ClockInService;
import com.wys.service.ClockOutService;
import com.wys.service.InOutService;
import com.wys.service.SelectClockInService;
import com.wys.service.SelectClockOutService;

/**
 * Servlet implementation class patternServlet
 */
// *.do로 설정된 패턴은 .do로 끝나는 접속주소를 모두 잡아옵니다.
// 확장자 패턴은 /를 빼고 작성합니다.
@WebServlet("*.do")
public class PatternServlet03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatternServlet03() {
        super();
        System.out.println("확장자 패턴 생성");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("확장자 패턴 서버 연결");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("확장자 패턴 소멸");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doRequest(request, response);
	}

	
	// 만약 요청 메서드(get, post) 상관 없이 처리하게 만들고 싶다면
	// 메서드 하나를 더 만들어서 요청한다.
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서비스 호출을 위해 모든 서비스 자료형을 받을 수 있는
		// 인터페이스를 생성합니다.
		// IBoardService sv = null;
		InOutService inOutService = null;
		//해당 로직을 실행한 뒤에 넘어갈 .jsp 파일 명칭/경로 지정
		String ui = null;
		
		// doGet에 있던 모든 코드를 가져옵니다.
		// 확장자 패턴에서 확장자를 포함한 주소값을 가져오기 위해서
		// 아래코드를 사용합니다.
		String uri = request.getRequestURI();
		System.out.println("uri패턴 : " + uri);
		
		// 콘솔이 아닌 사용자가 확인할 수 있도록 .jsp 화면에
		// 변수에 담긴 자료를 찍는 out.print(); 사용을 위한
		// 사전 준비
		request.setCharacterEncoding("UTF-8");
		// jsp페이지가 html형식으로 이뤄져 있음을 알려주는 코드
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		
		if(uri.equals("/ccs/commute.do")) {
			System.out.println("출근도장 확인");
			
			//1. 출근 시간
			inOutService = new SelectClockInService();
			inOutService.execute(request, response);
			
			ui = "/inout/commute.jsp";
			
		}else if(uri.equals("/ccs/doCommute.do")) {
			
			String inTime = request.getParameter("inTime");
			
			System.out.println("inTime ::: " + inTime);
			
			if(inTime != null && !"".equals(inTime)) {
				// 퇴근
				inOutService = new ClockOutService();
				inOutService.execute(request, response);
				System.out.println("퇴근 요청");
				
				System.out.println("111");
				
			}else {
				// 출근시 출근도장 확인 화면으로 이동
				inOutService = new ClockInService();
				inOutService.execute(request, response);
				System.out.println("출근 요청");
			
			}
			
			response.sendRedirect("http://127.0.0.1:8181/ccs/commute.do");
			return;
				
		}else {
			out.print("잘못된 패턴입니다.");
		}
		
		
		// 포워드 로직은 조건문이 모두 작동한 뒤에 실행합니다.
		// RequestDispatcher를 사용해 포워딩을 하면
		// request, response및 자료를 jsp페이지에 전달할 수 있습니다.
		// 모델2 방식은 스크립틀릿을 쓰지 않기 때문에
		// 컨트롤러단에서 출력에 필요한 데이터를 받아놨다가 포워드로 .jsp에 전달합니다.
		RequestDispatcher dp = request.getRequestDispatcher(ui);
		dp.forward(request, response);
	}
	
	
	
	
	
	
	
}
