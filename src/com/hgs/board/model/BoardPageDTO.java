package com.hgs.board.model;

import java.util.List;

public class BoardPageDTO {
	private int total;
	private int currentPage;
	private List<BoardVO> boardList;
	private int totalPages;
	private int startPage;
	private int endPage;

	public BoardPageDTO(int total, int currentPage, List<BoardVO> boardList) {
		this.total = total;
		this.currentPage = currentPage;
		this.boardList = boardList;

		if (total == 0) {
			this.totalPages = 0;
			this.startPage = 0;
			this.endPage = 0;
		} else {
			this.totalPages = total % 10 == 0 ? total / 10 : (total / 10) + 1;
			int modVal = this.currentPage % 10;
			this.startPage = this.currentPage / 10 * 10 + 1;
			if (modVal == 0)
				this.startPage -= 10;
			this.endPage = startPage + 9 > totalPages ? totalPages : startPage + 9;
		}
	}

	public int getTotal() {
		return total;
	}
	
	public boolean hasNoBoard() {
		return total == 0;
	}
	
	public boolean hasBoard() {
		return total > 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<BoardVO> getBoardList() {
		return boardList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	@Override
	public String toString() {
		return "BoardPageDTO [total=" + total + ", currentPage=" + currentPage + ", boardList=" + boardList
				+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
}
