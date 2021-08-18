package com.hgs.user.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.hgs.dept.model.DeptVO;

public class UserDAO {

	private DataSource ds;
	private static final int CHECK_ID_SUCCESS = 1;
	private static final int CHECK_ID_FAIL = 0;

	private UserDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static UserDAO dao = new UserDAO();

	public static UserDAO getInstace() {
		return dao;
	}
	// 회원가입 부서정보
	public List<DeptVO> getDept() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DeptVO> result = new ArrayList<DeptVO>();
		String sql = "SELECT * FROM dept";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDept_no(rs.getInt("dept_no"));
				dept.setDept_name(rs.getString("d_name"));
				result.add(dept);
			}
		} catch (SQLException e) {
			System.out.println("에러코드 : " + e);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}// end getDept
	// 회원강비 아이디 중복검사
	public int joinCheckId(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE m_id = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				return CHECK_ID_FAIL;
		} catch (SQLException e) {
			System.out.println("에러코드 : " + e);
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
		
		return CHECK_ID_SUCCESS;
	}
	// 회원가입
	public void join(UserVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member(m_name, m_id, m_pw, dept_no, m_phone, m_email)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getId());
			pstmt.setString(3, user.getPw());
			pstmt.setInt(4, user.getDept_no());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getEmail());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("에러코드 : " + e);
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
	// 로그인
	public UserVO login(UserVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserVO userInfo = new UserVO();
		String sql = "SELECT * from member WHERE m_id=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("m_pw").equals(user.getPw())) {
					userInfo.setName(rs.getString("m_name"));
					userInfo.setNo(rs.getInt("m_no"));
					userInfo.setId(rs.getString("m_id"));
					userInfo.setDept_no(rs.getInt("dept_no"));
					userInfo.setDept(getUserDept(userInfo.getDept_no()));
					userInfo.setPhone(rs.getString("m_phone"));
					userInfo.setEmail(rs.getString("m_email"));
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Error Code : " + e);
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
		
		return userInfo;
	}
	// 로그인 부서정보
	public String getUserDept(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dept = "";
		String sql = "SELECT * FROM dept WHERE dept_no=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				dept = rs.getString("d_name");
		} catch (SQLException e) {
			System.out.println("Error Code : " + e);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dept;
	}// end getUserDept
	// 회원정보 수정
	public void update(UserVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET m_pw=?, m_phone=?, m_email=?"
				+ "WHERE m_id=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPw());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("에러코드 : " + e);
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
	// 회원 정보 가져오기
	public List<UserVO> getUserPageList(int pageNum){
		List<UserVO> resultList = new ArrayList<UserVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member ORDER BY m_no ASC LIMIT ?, 10 ";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO user =  new UserVO();
				user.setName(rs.getString("m_name"));
				user.setNo(rs.getInt("m_no"));
				user.setId(rs.getString("m_id"));
				user.setPw(rs.getString("m_pw"));
				user.setDept_no(rs.getInt("dept_no"));
				user.setDept(getUserDept(user.getDept_no()));
				user.setPhone(rs.getString("m_phone"));
				user.setEmail(rs.getString("m_email"));
				resultList.add(user);
			}
		} catch (SQLException e) {
			System.out.println("에러코드 : " + e);
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
	// 회원 총 인원
	public int getUserCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "SELECT count(m_no) count FROM member";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {;
				cnt = rs.getInt("count");
			}
			return cnt;
			
		} catch (SQLException e) {
			System.out.println("에러코드 : " + e);
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
		return cnt;
	}
}
