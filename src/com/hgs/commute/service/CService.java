package com.hgs.commute.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CService {
	void execute(HttpServletRequest request, HttpServletResponse response);
}
