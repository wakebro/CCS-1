package com.wys.model;

import java.util.List;

public class InOutDTO {
	private int total;		// 전체 글 개수
	private int currentPage;	// 현재 보고 있는 페이지
	private List<InOutVO> boardList;	// 페이징 된 글 목록
	private int totalPages;	// 전체 페이지 개수
	private int startPage;	// 시작 페이지 번호
	private int endPage;	// 끝 페이지 번호
	
	public InOutDTO(int total, int currentPage, List<InOutVO> boardList) {
		this.total = total;
		this.currentPage = currentPage;
		this.boardList = boardList;
		
		if(total == 0) {
			this.totalPages = 0;
			this.startPage = 0;
			this.endPage = 0;
		}else {
			// 게시글 총 개수를 이용해 전체 페이지 개수부터 구하기.
			totalPages = total / 5;
			if(total % 5 > 0) {
				// 만약 나눴을때 5개로 딱 떨어지지 않으면
				// 마지막에 페이지를 하나 더 추가해야함
				this.totalPages += 1;
			}
			// 현재 보고 있는 페이지 그룹의 시작 번호 구하기
			int modVal = this.currentPage % 5;
			this.startPage = this.currentPage / 5 * 5 + 1;
			if(modVal == 0 ) {
				this.startPage -= 5;
			}
			// 해당 페이지 그룹의 끝번호 구하기
			this.endPage = this.startPage + (5 - 1);
			// 단, 위에서 구한 명목상의 마지막 페이지 번호가
			// totalPages를 초과하는 경우는
			// totalPages로 대신 대입힌다.
			if(endPage > totalPages) {
				endPage = totalPages;
			}
			
		}
	}
	public int getTotal() {
		return total;		// 총 글 개수 리턴
	}
	public boolean hasNoBoard() {
		return total == 0;	// 게시물 표기가 불가능할때 true리턴
	}
	public boolean hasBoard() {
		return total > 0;	// 게시물이 있을 때 true리턴
	}
	public int getTotalPages() {
		return totalPages;	// 페이지의 총 개수 리턴
	}
	public List<InOutVO> getBoardList() {
		return boardList;	// 해당페이지에 표기할 글 목록 리턴
	}
	public int getStartPage() {
		return startPage;	// 해당 페이지 그룹의 시작번호 리턴
	}
	public int getEndPage() {
		return endPage;		// 해당 페이지 그룹의 끝번호 리턴
	}

	@Override
	public String toString() {
		return "BoardPageDTO [total=" + total + ", currentPage=" + currentPage + ", boardList=" + boardList
				+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}
