package com.sjh.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;



public class MemberDetailService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		String idSession = (String) session.getAttribute("i_s");

		if (idSession == null) {
			try {
				// ���� ���ο��� �������� ��Ű��
				// �����̷�Ʈ�� �ƴϱ� ������ �����
				String ui = "/member/member_login_form.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// url�� ����� �� ��ȣ�� getParameter�� �̿��� ����ϴ�.
			String m_Id = request.getParameter("m_id");
	// DAO�� �����մϴ�.
			MemberDAO dao = MemberDAO.getinstance();
			
			MemberVO member = dao.getBoardDetail(m_Id);

			// �������� ���� setAttribute()�� �����͸� �Ǿ��ݴϴ�.
			request.setAttribute("member", member);
		}
	}
}
