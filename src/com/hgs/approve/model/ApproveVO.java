package com.hgs.approve.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ApproveVO {
	private SimpleDateFormat dateFormet = new SimpleDateFormat("yyyy-MM-dd");
	private int a_no;
	private String a_status;
	private String a_category;
	private String a_reason;
	private String d_name;
	private int m_no;
	private String m_name;
	private Timestamp a_start;
	private Timestamp a_end;
	private String a_start_;
	private String a_end_;
	private String a_head;
	
	public ApproveVO() {
	}
	
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public String getA_status() {
		return a_status;
	}
	public void setA_status(String a_status) {
		this.a_status = a_status;
	}
	public String getA_category() {
		return a_category;
	}
	public void setA_category(String a_category) {
		this.a_category = a_category;
	}
	public String getA_reason() {
		return a_reason;
	}
	public void setA_reason(String a_reason) {
		this.a_reason = a_reason;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public Timestamp getA_start() {
		return a_start;
	}
	public void setA_start(Timestamp a_start) {
		this.a_start = a_start;
	}
	public Timestamp getA_end() {
		return a_end;
	}
	public void setA_end(Timestamp a_end) {
		this.a_end = a_end;
	}
	public String getA_start_() {
		return a_start_;
	}
	public void setA_start_(Timestamp a_start) {
		this.a_start_ = dateFormet.format(this.a_start);
	}
	public String getA_end_() {
		return a_end_;
	}
	public void setA_end_(Timestamp a_end) {
		this.a_end_ = dateFormet.format(this.a_end);
	}
	public String getA_head() {
		return a_head;
	}
	public void setA_head(String a_head) {
		this.a_head = a_head;
	}
	@Override
	public String toString() {
		return "ApproveVO [a_no=" + a_no + ", a_status=" + a_status + ", a_category=" + a_category + ", a_reason="
				+ a_reason + ", d_name=" + d_name + ", m_no=" + m_no + ", m_name=" + m_name + ", a_start=" + a_start
				+ ", a_end=" + a_end + ", a_start_=" + a_start_ + ", a_end_=" + a_end_ + ", a_head=" + a_head + "]";
	}
}
