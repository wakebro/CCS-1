package com.hgs.board.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

public class BoardDAO {
	// 싱글턴 패턴과 커넥션풀을 적용
	// DAO의 연결부(생성자, getInstance())까지 작성
	private DataSource ds;
	
	public BoardDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static BoardDAO dao = new BoardDAO();
	
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	// 글 생성
	public int write(BoardVO board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int resultCode;
		
		String sql = "INSERT INTO board(m_id, b_title, b_content, b_date)"
				+ " VALUES(?, ?, ?, now())";
		
		try {
			con = ds.getConnection();
			
			// bId는 auto_increment가 붙어있으므로 입력 x
			// bName, bTitle, bContent는 폼에서 받아온 걸 넣음
			// bHit는 자동으로 0을 입력
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getm_id());
			pstmt.setString(2, board.getb_title());
			pstmt.setString(3, board.getb_content());
			
			pstmt.executeUpdate();
			resultCode = 1;
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
			resultCode = 0;
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
		
		return resultCode;
	}// end write
	// 글 목록
	public ArrayList<BoardVO> getBoardList() {
		ArrayList<BoardVO> boardList = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM board ORDER BY b_no DESC";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO list = new BoardVO();
				list.setb_no(rs.getInt("b_no"));
				list.setm_id(rs.getString("m_id"));
				list.setb_title(rs.getString("b_title"));
				list.setb_content(rs.getString("b_content"));
				list.setb_date(rs.getTimestamp("b_date"));
				list.setb_view(rs.getInt("b_view"));
				boardList.add(list);
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
		return boardList;
	}// end getBoardList
	//글 상세
	public BoardVO getBoardDetail(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVO content = new BoardVO();
		String sql = "SELECT * FROM board WHERE bid=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				content.setb_no(rs.getInt("b_no"));
				content.setm_id(rs.getString("m_id"));
				content.setb_title(rs.getString("b_title"));
				content.setb_content(rs.getString("b_content"));
				content.setb_date(rs.getTimestamp("b_date"));
				content.setb_view(rs.getInt("b_view"));
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
		return content;
	}// end getBoardDetail
}




























