package com.hgs.commute.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommuteDAO {
	private DataSource ds;
	
	public CommuteDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static CommuteDAO dao = new CommuteDAO();
	
	public static CommuteDAO getInstance() {
		if(dao == null)
			dao = new CommuteDAO();
		return dao;
	}
	
	// 출퇴근 전체 기록 확인
	public List<CommuteVO> bringDate(int m_no, int page) {
		List<CommuteVO> resultList = new ArrayList<CommuteVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT @rownum:=@rownum+1 num, A.* FROM\r\n" + 
				"(SELECT * FROM commute WHERE m_no=? ORDER BY c_no)A,\r\n" + 
				"(SELECT @rownum := 0) B ORDER BY num DESC LIMIT ?, 5";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			pstmt.setInt(2, page);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommuteVO commu = new CommuteVO();
				commu.setC_no(rs.getInt("num"));
				commu.setM_no(m_no);
				commu.setAttendance(rs.getTimestamp("attendance"));
				commu.setWork_leave(rs.getTimestamp("leave_work"));
				commu.setWork();
				commu.setLeave();
				resultList.add(commu);
			}
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}
	// 출퇴근 기록 개수
	public int getCommuteCount (int m_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "SELECT COUNT(*) count FROM commute WHERE m_no = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	// 최신 출퇴근 기록
	public CommuteVO bringLastestDate(int m_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommuteVO DBdate = new CommuteVO();
		String sql = "SELECT * FROM commute WHERE m_no = ? ORDER BY c_no DESC LIMIT 1";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				DBdate.setC_no(rs.getInt("c_no"));
				DBdate.setM_no(m_no);
				DBdate.setAttendance(rs.getTimestamp("attendance"));
				DBdate.setWork_leave(rs.getTimestamp("leave_work"));
			} else {
				DBdate = null;
			}
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return DBdate;
	}
	// 출근 기록하기
	public void write_work(int m_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Timestamp dateTime = new Timestamp(System.currentTimeMillis());
		
		String sql = "INSERT INTO commute(m_no, attendance) VALUES(?, ?)";		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			pstmt.setTimestamp(2, dateTime);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 퇴근 기록하기
	public void write_leave(int c_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE commute SET leave_work=? WHERE c_no=?";
		
		Timestamp dateTime = new Timestamp(System.currentTimeMillis());
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, dateTime);
			pstmt.setInt(2, c_no);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
