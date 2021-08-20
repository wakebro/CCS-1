package com.hgs.approve.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class A_CategoryDAO {
	private DataSource ds;
	
	private A_CategoryDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static A_CategoryDAO dao = new A_CategoryDAO();
	
	public static A_CategoryDAO getInstance() {
		if (dao == null)
			dao = new A_CategoryDAO();
		return dao;
	}
	
	// 휴가 종류 가져오기
	public List<A_CategoryVO> getCategory() {
		List<A_CategoryVO> resultList = new ArrayList<A_CategoryVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM a_category";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				A_CategoryVO list = new A_CategoryVO();
				list.setCate_no(rs.getInt("cate_no"));
				list.setCate_name(rs.getString("cate_name"));
				resultList.add(list);
			}
			return resultList;
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
		return resultList;
	}
}
