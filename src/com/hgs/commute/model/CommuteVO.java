package com.hgs.commute.model;

import java.sql.Date;
import java.sql.Timestamp;

public class CommuteVO {
	
	private int c_no;
	private int m_no;
	private Date today;
	private Timestamp attendance;
	private Timestamp leave_work;
	
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

	public Timestamp getAttendance() {
		return attendance;
	}

	public void setAttendance(Timestamp attendance) {
		this.attendance = attendance;
	}

	public Timestamp getLeave_work() {
		return leave_work;
	}

	public void setLeave_work(Timestamp leave_work) {
		this.leave_work = leave_work;
	}

	@Override
	public String toString() {
		return "CommuteVO [c_no=" + c_no + ", m_no=" + m_no + ", today=" + today + ", attendance=" + attendance
				+ ", leave_work=" + leave_work + "]";
	}
	
}
