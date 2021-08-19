package com.yjw.board.model;

import java.util.List;

public class BoardPageDTO {
	
	private int total;  // 전체 글 개수
	private int currentPage;  // 현재 페이지 번호
	private List<BoardVO> boardList;  // 페이징된 글 목록 
	private int totalPages;  // 페이지 개수
	private int startPage;  // 시작 페이지 번호
	private int endPage;  // 끝 페이지 번호 
	
	
	public BoardPageDTO(int total, int currentPage, List<BoardVO> boardList) {
		this.total = total;
		this.currentPage = currentPage;
		this.boardList = boardList;
		
		if(total == 0) {
			this.totalPages = 0;
			this.startPage = 0;
			this.endPage = 0;
		} else {
			// 1. 전체 페이지 개수부터 구하기
			totalPages = total / 10;
			if(total % 10 > 0) {
				// 한 페이지당 10으로 나눴을 때 딱 맞아떨어지지 않으면 페이지를 하나 더 추가
				this.totalPages += 1;
			}
			
			// 2. 현재 보고 있는 페이지의 번호가 속한 그룹의 시작 번호 구하기
			int whatYourGroup = this.currentPage % 10;
			this.startPage = this.currentPage / 10 * 10 + 1;
			if(whatYourGroup == 0) {
				// 현재 페이지를 10으로 나눴을 때 딱 맞아 떨어진다면 이전 그룹으로 인식
				this.startPage -= 10;
			}
			
			// 3. 해당 페이지의 그룹의 끝 번호 구하기
			this.endPage = this.startPage + 9;
			// 해당 페이지 그룹의 끝번호가 전체 페이지 개수를 초과하면 전체 페이지 개수를 대입
			if (endPage > totalPages) {
				endPage = totalPages;
			}
			
		}
	}
	// END Constructor


	public int getTotal() {
		return total;  
	}

	
	public boolean hasNoBoard() {
		return total == 0;  // 게시물이 존재하지 않는 경우에 true리턴
	}

	
	public boolean hasBoard() {
		return total > 0;  // 게시물이 존재할 때  true리턴
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
