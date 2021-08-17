package com.hgs.user.model;

import java.util.List;

public class UserPageDTO {
	private int total;
	private int currentPage;
	private List<UserVO> userList;
	private int totalPage;
	private int startPage;
	private int endPage;
	
	public UserPageDTO(int total, int currentPage, List<UserVO> userList) {
		this.total = total;
		this.currentPage = currentPage;
		this.userList = userList;
		
		if(total == 0) {
			this.total = 0;
			this.startPage = 0;
			this.endPage = 0;
		} else {
			this.totalPage = (total % 10) > 0 ? (total / 10) + 1 : total / 10;
			int temp = this.currentPage % 10;
			this.startPage = this.currentPage / 10 * 10 + 1;
			if (temp == 0) 
				this.startPage -= 10;
			this.endPage = startPage + 9 > totalPage ? totalPage : startPage + 9;
		}
		
	}

	public int getTotal() {
		return total;
	}

	public boolean hasNoBoard() {
		return this.total==0;
	}
	
	public boolean hasBoard() {
		return this.total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public List<UserVO> getUserList() {
		return userList;
	}

	public int getTotalPage() {
		return totalPage;
	}


	public int getStartPage() {
		return startPage;
	}


	public int getEndPage() {
		return endPage;
	}

	@Override
	public String toString() {
		return "UserPageDTO [total=" + total + ", currentPage=" + currentPage + ", userList=" + userList
				+ ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
}
