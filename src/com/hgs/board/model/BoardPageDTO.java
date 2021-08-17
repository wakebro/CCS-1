package com.hgs.board.model;

import java.util.List;

// VO와 DTO는 크게 구분을 하지 않는 사람들도 있고 구분을 한다고 해도
// 엄격하게 하지 않는 편
// 만약, 굳이 구분을 해야 한다면,
// VO는 DB에서 받아온 데이터를 그대로 전달
// DTO는 받아온 데이터를 특수하게 가공하여 전달 
public class BoardPageDTO {
	private int total;; // 전체 글 개수
	private int currentPage; // 현재 보고 있는 페이지
	private List<BoardVO> boardList; // 페이징된 글 목록
	private int totalPages; // 전체 페이지 개수
	private int startPage; // 시작 페이지 번호
	private int endPage; // 끝 페이지 번호

	// 생성자는 전체 글 개수, 현재 페이지, 페이징된 글 목록을 받아서 가공하여
	// 나머지 정보를 산출

	public BoardPageDTO(int total, int currentPage, List<BoardVO> boardList) {
		this.total = total;
		this.currentPage = currentPage;
		this.boardList = boardList;

		// 아래부터 위 3개를 토대로 가공하여 나머지 변수 값을 산출
		// 글이 없는 경우 페이지 및 버튼이 필요 없음
		if (total == 0) {
			this.totalPages = 0;
			this.startPage = 0;
			this.endPage = 0;
		} else {
			// 게시글 총 개수를 이용해 전체 페이지 개수부터 구하기
			this.totalPages = total % 10 == 0 ? total / 10 : (total / 10) + 1;
			// 현재 보고 있는 페이지 그룹의 시작번호와 끝번호
			int modVal = this.currentPage % 10;
			this.startPage = this.currentPage / 10 * 10 + 1;
			if (modVal == 0)
				this.startPage -= 10;
//			this.startPage = modVal == 0 ? ((this.currentPage / 10) - 1) * 10 +1: 
//											this.currentPage / 10 * 10 +1;
			this.endPage = startPage + 9 > totalPages ? totalPages : startPage + 9;
		}
	}

	public int getTotal() {
		return total;
	}
	
	public boolean hasNoBoard() {
		return total == 0;		// 게시물 표기가 불가능할 때 true 리턴
	}
	
	public boolean hasBoard() {
		return total > 0;		// 게시물이 있을때 true 리턴
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<BoardVO> getBoardList() {
		return boardList;		// 해당 페이지에 표기할 글 목록 리턴
	}

	public int getTotalPages() {
		return totalPages;		// 페이지 총 개수 리턴
	}

	public int getStartPage() {
		return startPage;		// 해당 페이지 그룹의 시작번호 리턴
	}

	public int getEndPage() {
		return endPage;			// 해당 페이지 그룹의 끝번호 리턴
	}

	@Override
	public String toString() {
		return "BoardPageDTO [total=" + total + ", currentPage=" + currentPage + ", boardList=" + boardList
				+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
}
