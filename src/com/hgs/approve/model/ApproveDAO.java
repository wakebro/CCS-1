package com.hgs.approve.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ApproveDAO {
	private static DataSource ds;
	
	private ApproveDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ApproveDAO dao = new ApproveDAO();
	
	public static ApproveDAO getInstance() {
		if(dao == null)
			dao = new ApproveDAO();
		return dao;
	}
}
