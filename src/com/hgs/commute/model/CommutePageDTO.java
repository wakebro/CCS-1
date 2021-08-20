package com.hgs.commute.model;

import java.util.List;

public class CommutePageDTO {
	private int total;
	private int currentPage;
	private List<CommuteVO> commuteList;
	private int totalPage;
	private int startPage;
	private int endPage;
	
	public CommutePageDTO(int total, int currentPage, List<CommuteVO> commuteList) {
		this.total = total;
		this.currentPage = currentPage;
		this.commuteList = commuteList;
		
		if(total == 0) {
			this.totalPage = 0;
			this.startPage = 0;
			this.endPage = 0;
		} else {
			this.totalPage = total % 5 > 0 ? (total / 5) + 1 : total / 5;
			int temp = this.currentPage % 10;
			this.startPage = this.currentPage / 10 * 10 + 1;
			if (temp == 0)
				this.startPage -= 5;
			this.endPage = this.startPage + 9 > totalPage ? totalPage : this.startPage + 9;
		}
	}

	public int getTotal() {
		return total;
	}
	
	public boolean hasNoCommute() {
		return total == 0;
	}
	
	public boolean hasCommute() {
		return total > 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<CommuteVO> getCommuteList() {
		return commuteList;
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
		return "CommutePageDTO [total=" + total + ", currentPage=" + currentPage + ", commuteList=" + commuteList
				+ ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
}
