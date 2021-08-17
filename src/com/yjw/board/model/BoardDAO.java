package com.yjw.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.yjw.board.model.BoardDAO;

public class BoardDAO {

	private DataSource ds;
	
	private static BoardDAO dao = new BoardDAO();
	
	private BoardDAO() {
		try { 
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	
	public int writeBoard(BoardVO board) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int resultCode;
		String sql = "INSERT INTO board (b_title, b_content, m_id, b_date, b_view) VALUES (?, ?, ?, now(), 0)";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getM_id());
			pstmt.executeUpdate();
			
			// 입력 확인
			System.out.println(pstmt);
			resultCode = 1;
		} catch(Exception e) {
			e.printStackTrace();
			resultCode = 0;
		} finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultCode;
	} 
	// END writeBoard
	
	
	public List<BoardVO> getBoardList() {
		
		List<BoardVO> boardList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM board ORDER BY b_no DESC";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setB_no(rs.getInt("b_no"));
				board.setM_id(rs.getString("m_id"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_date(rs.getTimestamp("b_date"));
				board.setB_view(rs.getInt("b_view"));
				
				boardList.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs!=null && !rs.isClosed()) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return boardList;
	}  
	// END getBoardList
	
	
	
	
	
	
	
	
	
	
	
	
}
