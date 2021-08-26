package com.sjh.service;

<<<<<<< HEAD

=======
>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import com.hgs.user.model.UserDAO;
=======
>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;


public class MemberLoginService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
<<<<<<< HEAD
			HttpSession session = request.getSession();
			
			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
			
			if (m_id == null) {
				try {
					RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
			
			MemberDAO dao = MemberDAO.getinstance();
			MemberVO user = new MemberVO();
			MemberVO userInfo = new MemberVO();
			UserDAO uDao = UserDAO.getInstace();
			
			user.setM_Id(m_id);
			user.setM_Pw(m_pw);
			userInfo = dao.login(user);
			userInfo.setDept(uDao.getUserDept(userInfo.getDept_no()));
			String userDept = user.getDept();
			
			// ë¡œê·¸ì¸
			if(userInfo.getM_Id() == null) {
				try {
					session.invalidate();
					RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(userInfo.getDept_no()==1000) {
				session.setAttribute("admin", userInfo.getDept());
			}
			System.out.println(userInfo);
			
			// ì„¸ì…˜ ìƒì„±
			session.setAttribute("session_id", userInfo.getM_Id());
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("userDept", userDept);
		}
	}	
=======
		try {
			String m_Id = request.getParameter("m_Id");
			String m_Pw = request.getParameter("m_Pw");
			
			HttpSession session = null;
			session = request.getSession();
			String idSession = (String) session.getAttribute("i_s");
			
			// ÇÑ±Û±úÁü ¹æÁö
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			// È¸¿ø°¡ÀÔ Æû¿¡¼­ ´øÁø ÆÄ¶ó¹ÌÅÍ °¡Á®¿À±â
			
			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
	
			MemberDAO dao = MemberDAO.getinstance();
			int resultCode = dao.login(m_id, m_pw);
			if(resultCode == 1) {
				session.setAttribute("i_s", m_Id);
				session.setAttribute("p_s", m_Pw);
				session.setAttribute("login", "success");
			}else if(resultCode==0) {
				session.setAttribute("login", "fail");
			}
			
			MemberVO member = new MemberVO();
			
		
			member.setM_Id(m_id);
			member.setM_Pw(m_pw);
			dao.login(m_id, m_pw);
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
>>>>>>> d291b6be71761378553e80bc11b202550cf4e4c2
}
