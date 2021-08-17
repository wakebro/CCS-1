package com.hgs.board.model;

import java.sql.Timestamp;

public class BoardVO {
	
	private int b_no;
	private String m_id;
	private String b_title;
	private String b_content;
	private Timestamp b_date;	// Timestamp는 시 분 초까지 표기, Date는 년 월 일까지 표기
	private int b_view;
	
	
	// 생성자, setter/getter, toString 생성
	
	// 아무것도 안받는 생성자
	public BoardVO() {
	}
	
	// 모든 요소를 다 받는 생성자
	public BoardVO(int b_no, String m_id, String b_title, String b_content, Timestamp b_date, int b_view) {
		super();
		this.b_no = b_no;
		this.m_id = m_id;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_date = b_date;
		this.b_view = b_view;
	}


	public int getb_no() {
		return b_no;
	}

	public void setb_no(int b_no) {
		this.b_no = b_no;
	}

	public String getm_id() {
		return m_id;
	}

	public void setm_id(String m_id) {
		this.m_id = m_id;
	}

	public String getb_title() {
		return b_title;
	}

	public void setb_title(String b_title) {
		this.b_title = b_title;
	}

	public String getb_content() {
		return b_content;
	}

	public void setb_content(String b_content) {
		this.b_content = b_content;
	}

	public Timestamp getb_date() {
		return b_date;
	}

	public void setb_date(Timestamp b_date) {
		this.b_date = b_date;
	}

	public int getb_view() {
		return b_view;
	}

	public void setb_view(int b_view) {
		this.b_view = b_view;
	}

	@Override
	public String toString() {
		return "BoardVO [b_no=" + b_no + ", m_id=" + m_id + ", b_title=" + b_title + ", b_content=" + b_content + ", b_date="
				+ b_date + ", b_view=" + b_view + "]";
	}
	
	

}
