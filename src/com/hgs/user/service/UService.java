package com.hgs.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UService {
	void execute(HttpServletRequest request, HttpServletResponse response);
}
