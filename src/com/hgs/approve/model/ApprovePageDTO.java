package com.hgs.approve.model;

import java.util.List;

public class ApprovePageDTO {
	private int total;
	private int totalPages;
	private int currentPage;
	private int startPage;
	private int endPage;
	private List<ApproveVO> approveList;
	
	public ApprovePageDTO(int total, int currentPage, List<ApproveVO> approveList) {
		this.total = total;
		this.currentPage = currentPage;
		this.approveList = approveList;
		
		if (this.total == 0) {
			this.totalPages = 0;
			this.startPage = 0;
			this.endPage = 0;
		}
		
		this.totalPages = (this.total % 10) > 0 ? (this.total / 10) + 1 : (this.total / 10);
		int temp = this.currentPage % 10;
		this.startPage = (this.currentPage / 10) * 10 + 1;
		if(temp == 0)
			this.startPage -= 10;
		this.endPage = this.startPage + 9 > this.totalPages ? this.totalPages : this.startPage + 9;
	}
	
	public boolean hasList() {
		return total > 0;
	}
	
	public boolean hasNoList() {
		return total == 0;
	}

	public int getTotal() {
		return total;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public List<ApproveVO> getApproveList() {
		return approveList;
	}
	
	
}
