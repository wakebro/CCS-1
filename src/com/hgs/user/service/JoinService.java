package com.hgs.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hgs.dept.model.DeptVO;
import com.hgs.user.model.UserDAO;

public class JoinService implements UService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		UserDAO dao = UserDAO.getInstace();
		List<DeptVO> deptlist = dao.getDept();
		request.setAttribute("deptlist", deptlist);
	}
}
