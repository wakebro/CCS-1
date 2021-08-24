package com.wys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class InOutDAO {
	
	private static final int SUCCESS = 1;
	private static final int FAIL = 0;

	// 객체 생성
	private static InOutDAO dao = new InOutDAO();
	
	private DataSource ds;
	
	// 싱글턴 패턴
	private InOutDAO() {
		
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 외부에서 객체 생성이 필요할 때 리턴
	public static InOutDAO getInstance() {
		if(dao == null) {
			dao = new InOutDAO();
		}
		return dao;
	}
	
	
	// START List<InOutVO>
	// 모든 출퇴근 정보를 DB로부터 받아올 메서드
	public List<InOutVO> getCommuteList(InOutVO inOutVO){
		List<InOutVO> commuteList = new ArrayList<InOutVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// where m_no 에 세션 사원번호가 들어가야함
		String sql = "SELECT m_no, m_name, STR_TO_DATE(clock_in_time, '%Y%m%d%H%i%s') AS clock_in_time, "
				+ "STR_TO_DATE(clock_out_time, '%Y%m%d%H%i%s') AS clock_out_time "
				+ "FROM commute "
				+ "ORDER BY clock_in_time";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				InOutVO commute = new InOutVO();
				
				commute.setM_no(rs.getInt("m_no"));
				commute.setM_name(rs.getString("m_name"));
				commute.setClock_in_time(rs.getString("clock_in_time"));
				commute.setClock_out_time(rs.getString("clock_out_time"));
				
				commuteList.add(commute);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
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
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return commuteList;
	}
	// 모든 출퇴근 정보를 DB로부터 받아올 메서드
		public List<InOutVO> getCommuteList(InOutVO inOutVO, int pageNum){
			List<InOutVO> commuteList = new ArrayList<InOutVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// 세션 사원번호가 들어가야함
			String sql = "SELECT m_no, m_name, STR_TO_DATE(clock_in_time, '%Y%m%d%H%i%s') AS clock_in_time, "
					+ "STR_TO_DATE(clock_out_time, '%Y%m%d%H%i%s') AS clock_out_time "
					+ "FROM commute WHERE m_no=? "
					+ "ORDER BY clock_in_time DESC "
					+ "LIMIT ?, 5"
					;		
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, inOutVO.getM_no());
				pstmt.setInt(2, pageNum);

				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					InOutVO commute = new InOutVO();
					
					commute.setM_no(rs.getInt("m_no"));
					commute.setM_name(rs.getString("m_name"));
					commute.setClock_in_time(rs.getString("clock_in_time"));
					commute.setClock_out_time(rs.getString("clock_out_time"));
					
					commuteList.add(commute);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
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
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			return commuteList;
		}

	
	// START clockIn (출근시간 입력)
	public int clockIn(InOutVO inOut) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO commute (m_no, m_name, clock_in_time) "
				+ "VALUES(?, ?, DATE_FORMAT(now(), '%Y%m%d%H%i%s'))";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, inOut.getM_no());
			pstmt.setString(2, inOut.getM_name());
			
			pstmt.executeUpdate();
			// pstmt.executeQuery();
			
			return SUCCESS;
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(con!=null && !con.isClosed()){
					con.close();
				}if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return FAIL; 
	}// END clockIn
	
	
	
	
	// START selectClockIn (출근버튼 입력 후 조회)
	public InOutVO selectClockIn(InOutVO inOut) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT " + 
						" m_no, m_name, STR_TO_DATE(clock_in_time, '%Y%m%d%H%i%s') AS clock_in_time, STR_TO_DATE(clock_out_time, '%Y%m%d%H%i%s') AS clock_out_time " +
					" FROM commute " + 
					" WHERE m_no = ? " + 
					" AND STR_TO_DATE(clock_in_time, '%Y%m%d') = STR_TO_DATE(DATE_FORMAT(now(), '%Y%m%d'), '%Y%m%d')";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, inOut.getM_no());
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				inOut.setM_no(rs.getInt("m_no"));
				inOut.setM_name(rs.getString("m_name"));
				inOut.setClock_in_time(rs.getString("clock_in_time"));
				inOut.setClock_out_time(rs.getString("clock_out_time"));
			}
			
			return inOut;
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(con!=null && !con.isClosed()){
					con.close();
				}if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return inOut; 
	}// END selectClockIn
	
	
	
	
	// START selectClockOut (퇴근버튼 입력 후 조회)
		public InOutVO selectClockOut(InOutVO inOut) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT " + 
							" m_no, m_name, STR_TO_DATE(clock_in_time, '%Y%m%d%H%i%s') AS clock_in_time, clock_out_time, STR_TO_DATE(clock_out_time, '%Y%m%d%H%i%s') AS clock_out_time " +
						" FROM commute " + 
						" WHERE m_no = ? " + 
						" AND STR_TO_DATE(clock_in_time, '%Y%m%d') = STR_TO_DATE(DATE_FORMAT(now(), '%Y%m%d'), '%Y%m%d') "
						+ "AND STR_TO_DATE(clock_out_time, '%Y%m%d') = STR_TO_DATE(DATE_FORMAT(now(), '%Y%m%d'), '%Y%m%d')";
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, inOut.getM_no());
				
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {
					inOut.setM_no(rs.getInt("m_no"));
					inOut.setM_name(rs.getString("m_name"));
					inOut.setClock_in_time(rs.getString("clock_in_time"));
					inOut.setClock_out_time(rs.getString("clock_out_time"));
				}
				
				return inOut;
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				try{
					if(con!=null && !con.isClosed()){
						con.close();
					}if(pstmt!=null && !pstmt.isClosed()){
						pstmt.close();
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			return inOut; 
		}// END selectClockOut
		
		
		
		
		// start clock out (퇴근)
		public int clockOut(InOutVO inOut) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			String sql ="UPDATE commute SET " + 
					"clock_out_time = DATE_FORMAT(now(), '%Y%m%d%H%i%s') " + 
					"WHERE m_no = ? " +
					"AND DATE_FORMAT(now(), '%Y%m%d') = DATE_FORMAT(clock_in_time, '%Y%m%d')";
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, inOut.getM_no());
				
				pstmt.executeUpdate();
				// pstmt.executeQuery();
				
				return SUCCESS;
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				try{
					if(con!=null && !con.isClosed()){
						con.close();
					}if(pstmt!=null && !pstmt.isClosed()){
						pstmt.close();
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			return FAIL; 
		}// END clockOut
		
		
		
		
		
	
	
	

	// start List<InOutVO> (페이징로직) 
	public List<InOutVO>getPageList(int pageNum) {
		List<InOutVO> commuteList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM commute ORDER BY m_no DESC LIMIT ?, 5";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, pageNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				InOutVO commute = new InOutVO();
				commute.setM_no(rs.getInt("m_no"));
				commute.setM_name(rs.getString("m_name"));
				commute.setClock_in_time(rs.getString("clock_in_time"));
				commute.setClock_out_time(rs.getString("clock_out_time"));
				
				commuteList.add(commute);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return commuteList;
	}// END List<InOutVO>
	
	
	
	
	
	
	// start 페이지 합산
	public int getCommuteListCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		String sql = "SELECT count(*) FROM commute";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			//pstmt.setInt(1, inOutVO.getM_no());
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
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
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}// END paging
	
	
	
	
	
	
}
	
	
	

