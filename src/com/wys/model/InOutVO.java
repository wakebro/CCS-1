package com.wys.model;

public class InOutVO {
	private String m_name;
	private int m_no;
	private String clock_in_time;
	private String clock_out_time;
	
	public InOutVO() {
		
	}
	// 모든 요소를 다 받는 생성자

	public InOutVO(String m_name, int m_no, String clock_in_time, String clock_out_time) {
		super();
		this.m_name = m_name;
		this.m_no = m_no;
		this.clock_in_time = clock_in_time;
		this.clock_out_time = clock_out_time;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public String getClock_in_time() {
		return clock_in_time;
	}

	public void setClock_in_time(String clock_in_time) {
		this.clock_in_time = clock_in_time;
	}

	public String getClock_out_time() {
		return clock_out_time;
	}

	public void setClock_out_time(String clock_out_time) {
		this.clock_out_time = clock_out_time;
	}

	@Override
	public String toString() {
		return "InOutVO [m_name=" + m_name + ", m_no=" + m_no + ", clock_in_time=" + clock_in_time + ", clock_out_time="
				+ clock_out_time + "]";
	}
	

}