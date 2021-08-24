package com.sjh.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;

public class MemberListService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		String idSession = (String) session.getAttribute("i_s");

		if (idSession == null) {
			try {
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				// ���� ���ο��� �������� ��Ű��
				// �����̷�Ʈ�� �ƴϱ� ������ �����
				String ui = "/member/member_login_form.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// DAO����
			MemberDAO dao = MemberDAO.getinstance();
			//��ü ��� ����Ʈ ��������
			ArrayList<MemberVO> memberList = dao.getAllMember();
			
			// �޾ƿ� ����Ʈ�� .jsp�� �����ϱ�.
			// request�� �����͸� �Ǿ���� �մϴ�.
			// request.setAttribute("��Ī", ������);
			request.setAttribute("memberList", memberList);
		}
	}

}
