package com.sjh.model;

public class MemberVO {
	private String m_Name;
	private int m_No;
	private String m_Id;
	private String m_Pw;
	private int Dept_no;
	private String dept;
	private String m_Phone;
	private String m_Email;
	
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getM_Name() {
		return m_Name;
	}
	
	public void setM_Name(String m_Name) {
		this.m_Name = m_Name;
	}
	public int getM_No() {
		return m_No;
	}
	public void setM_No(int m_No) {
		this.m_No = m_No;
	}
	public String getM_Id() {
		return m_Id;
	}
	public void setM_Id(String m_Id) {
		this.m_Id = m_Id;
	}
	public String getM_Pw() {
		return m_Pw;
	}
	public void setM_Pw(String m_Pw) {
		this.m_Pw = m_Pw;
	}
	public int getDept_no() {
		return Dept_no;
	}
	public void setDept_no(int dept_no) {
		Dept_no = dept_no;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getM_Phone() {
		return m_Phone;
	}
	public void setM_Phone(String m_Phone) {
		this.m_Phone = m_Phone;
	}
	public String getM_Email() {
		return m_Email;
	}
	public void setM_Email(String m_Email) {
		this.m_Email = m_Email;
	}
	@Override
	public String toString() {
		return "MemberVO [m_Name=" + m_Name + ", m_No=" + m_No + ", m_Id=" + m_Id + ", m_Pw=" + m_Pw + ", Dept_no="
				+ Dept_no + ", dept=" + dept + ", m_Phone=" + m_Phone + ", m_Email=" + m_Email + "]";
	}
	
	
	
	
}
