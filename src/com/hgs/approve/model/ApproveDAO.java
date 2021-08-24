package com.hgs.approve.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	// 결재 쓰기
	public void writeApprove(ApproveVO approve) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO approval(a_category, a_start, a_end, a_reason, d_name, m_no, m_name) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, approve.getA_category());
			pstmt.setTimestamp(2, approve.getA_start());
			pstmt.setTimestamp(3, approve.getA_end());
			pstmt.setString(4, approve.getA_reason());
			pstmt.setString(5, approve.getD_name());
			pstmt.setInt(6, approve.getM_no());
			pstmt.setString(7, approve.getM_name());
			
			pstmt.executeUpdate();
			
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
	// 자기 결재 기록
	public List<ApproveVO> getMyApprove(int m_no, int page){
		List<ApproveVO> approveList = new ArrayList<ApproveVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM approval WHERE m_no=? ORDER BY a_no DESC LIMIT ?, 10";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			pstmt.setInt(2, page);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ApproveVO list = new ApproveVO();
				list.setA_status(rs.getString("a_status"));
				list.setA_category(rs.getString("a_category"));
				list.setA_start(rs.getTimestamp("a_start"));
				list.setA_end(rs.getTimestamp("a_end"));
				list.setA_reason(rs.getString("a_reason"));
				list.setA_head(rs.getString("a_head"));
				list.setA_start_(list.getA_start());
				list.setA_end_(list.getA_end());
				approveList.add(list);
			}
			return approveList;
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
		return approveList;
	}
	// 자기 결제 전체 수
	public int getMyTotalCount(int m_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) count FROM approval WHERE m_no=?";
		int total = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				total = rs.getInt("count");
			
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
		return total;
	}
	// 전체 결재 기록
	public List<ApproveVO> getAllApprove(int page){
		List<ApproveVO> approveList = new ArrayList<ApproveVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM approval ORDER BY a_no DESC LIMIT ?, 10";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ApproveVO list = new ApproveVO();
				list.setA_no(rs.getInt("a_no"));
				list.setA_category(rs.getString("a_category"));
				list.setD_name(rs.getString("d_name"));
				list.setM_no(rs.getInt("m_no"));
				list.setM_name(rs.getString("m_name"));
				list.setA_reason(rs.getString("a_reason"));
				list.setA_start(rs.getTimestamp("a_start"));
				list.setA_end(rs.getTimestamp("a_end"));
				list.setA_start_(list.getA_start());
				list.setA_end_(list.getA_end());
				list.setA_head(rs.getString("a_head"));
				list.setA_status(rs.getString("a_status"));
				approveList.add(list);
			}
			return approveList;
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
		return approveList;
	}
	// 전 사원 결제 신청 수
	public int getAllTotalCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) count FROM approval";
		int total = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				total = rs.getInt("count");
			
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
		return total;
	}
	// 관리자 전용 결재 내용 확인
	public ApproveVO getDetailApprove(String a_no) {
		ApproveVO result = new ApproveVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM approval WHERE a_no=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a_no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result.setA_no(rs.getInt("a_no"));
				result.setA_category(rs.getString("a_category"));
				result.setA_status(rs.getString("a_status"));
				result.setA_reason(rs.getString("a_reason"));
				result.setD_name(rs.getString("d_name"));
				result.setM_no(rs.getInt("m_no"));
				result.setM_name(rs.getString("m_name"));
				result.setA_start(rs.getTimestamp("a_start"));
				result.setA_end(rs.getTimestamp("a_end"));
				result.setA_start_(result.getA_start());
				result.setA_end_(result.getA_end());
				result.setA_head(rs.getString("a_head"));
			}
			
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
		return result;
	}
	// 관리자 전용 결재 확인
	public void confirmApprove(String a_no, String a_status, String a_reason, String a_head) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE approval SET a_status=?, a_reason=?, a_head=? WHERE a_no=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a_status);
			pstmt.setString(2, a_reason);
			pstmt.setString(3, a_head);
			pstmt.setString(4, a_no);
			
			pstmt.executeUpdate();
			
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
