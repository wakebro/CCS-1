package com.hgs.approve.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public void writeApprove(ApproveVO approve) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO approval() VALUES(?, ?, ?, ?)";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try{
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
