package com.wys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface InOutService {

	void execute(HttpServletRequest request, HttpServletResponse response);
	// void executeClockIn(HttpServletRequest request, HttpServletResponse response);
	
	
}
