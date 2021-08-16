package com.hgs.user.model;

public class UserVO {
	private String name;
	private int no;
	private String id;
	private String pw;
	private int dept_no;
	private String phone;
	private String email;
	
	public UserVO() {
	}

	public UserVO(String name, int no, String id, String pw, int dept_no, String phone, String email) {
		super();
		this.name = name;
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.dept_no = dept_no;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserVO [name=" + name + ", no=" + no + ", id=" + id + ", pw=" + pw + ", dept_no=" + dept_no + ", phone="
				+ phone + ", email=" + email + "]";
	}
	
	
}
