package com.hgs.commute.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommuteVO {
	
	private int c_no;
	private int m_no;
	private Date today;
	private Format attendance;
	private Timestamp work_leave;
	
	public CommuteVO() {
		
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public SimpleDateFormat getAttendance() {
		return attendance;
	}

	public void setAttendance(SimpleDateFormat attendance) {
		this.attendance = attendance;
	}

	public SimpleDateFormat getWork_leave() {
		return work_leave;
	}

	public void setWork_leave(SimpleDateFormat work_leave) {
		this.work_leave = work_leave;
	}

	@Override
	public String toString() {
		return "CommuteVO [c_no=" + c_no + ", m_no=" + m_no + ", today=" + today + ", attendance=" + attendance
				+ ", work_leave=" + work_leave + "]";
	}
	
}
