package com.hgs.commute.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommuteVO {
	
	private int c_no;
	private int m_no;
	private String work;
	private String leave ;
	private Timestamp attendance;
	private Timestamp work_leave;
	private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
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
	public String getWork() {
		return work;
	}
	public void setWork() {
		if(this.attendance == null) {
			this.work = null;
		} else {
			this.work = date.format(this.attendance);
		}
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave() {
		if(this.work_leave == null) {
			this.leave = null;
		} else {
			this.leave = date.format(this.work_leave);
		}
	}
	public Timestamp getAttendance() {
		return attendance;
	}
	public void setAttendance(Timestamp attendance) {
		this.attendance = attendance;
	}
	public Timestamp getWork_leave() {
		return work_leave;
	}
	public void setWork_leave(Timestamp work_leave) {
		this.work_leave = work_leave;
	}
	
	
	
}
