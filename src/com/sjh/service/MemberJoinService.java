package com.sjh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;

public class MemberJoinService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");

			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
			String m_name = request.getParameter("m_name");
			String strdept_no=request.getParameter("dept_no");
			int dept_no = Integer.parseInt(strdept_no);
			String m_phone = request.getParameter("m_phone");
			String m_email = request.getParameter("m_email");
			
			MemberDAO dao = MemberDAO.getinstance();
			
			MemberVO member = new MemberVO();
			member.setM_Name(m_name);
			member.setM_Id(m_id);
			member.setM_Pw(m_pw);
			member.setDept_no(dept_no);
			member.setM_Phone(m_phone);
			member.setM_Email(m_email);
			System.out.println(member);
			dao.joinMember(member);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
