package com.yjw.board.model;

import java.sql.Timestamp;

public class BoardVO {
	
	private int b_no;
	private String m_id;
	private String b_title;
	private String b_content;
	private Timestamp b_date;
	private int b_view;
	
	
	public BoardVO() {
		
	}

	
	public BoardVO(int b_no, String m_id, String b_title, String b_content, Timestamp b_date, int b_view) {
		super();
		this.b_no = b_no;
		this.m_id = m_id;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_date = b_date;
		this.b_view = b_view;
	}


	@Override
	public String toString() {
		return "BoardVO [b_no=" + b_no + ", m_id=" + m_id + ", b_title=" + b_title + ", b_content=" + b_content
				+ ", b_date=" + b_date + ", b_view=" + b_view + "]";
	}


	public int getB_no() {
		return b_no;
	}


	public void setB_no(int b_no) {
		this.b_no = b_no;
	}


	public String getM_id() {
		return m_id;
	}


	public void setM_id(String m_id) {
		this.m_id = m_id;
	}


	public String getB_title() {
		return b_title;
	}


	public void setB_title(String b_title) {
		this.b_title = b_title;
	}


	public String getB_content() {
		return b_content;
	}


	public void setB_content(String b_content) {
		this.b_content = b_content;
	}


	public Timestamp getB_date() {
		return b_date;
	}


	public void setB_date(Timestamp b_date) {
		this.b_date = b_date;
	}


	public int getB_view() {
		return b_view;
	}


	public void setB_view(int b_view) {
		this.b_view = b_view;
	}
	
}
