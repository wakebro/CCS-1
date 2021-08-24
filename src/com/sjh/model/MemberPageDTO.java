package com.sjh.model;
import java.util.ArrayList;
//import java.util.List;
public class MemberPageDTO {
	private int total;  // �쟾泥� �궗�썝 媛쒖닔
	private int currentPage; // �쁽�옱 蹂닿퀬 �엳�뒗 �럹�엸
	private ArrayList<MemberVO> memberList;
	private int totalPages; // �쟾泥� �럹�씠吏� 媛쒖닔
	private int startPage; // �떆�옉 �럹�씠吏� 踰덊샇
	private int endPage; // �쓠 �럹�씠吏� 踰덊샇
	
	public MemberPageDTO(int total, int currentPage,ArrayList<MemberVO> memberList) {
		this.total = total;
		this.currentPage = currentPage;
		this.memberList = memberList;
		
		if(total == 0) {
			this.totalPages=0;
			this.startPage =0;
			this.endPage =0;
		}else {
		this.totalPages = total/5;
		if(this.total % 5 >0) {
			this.totalPages += 1;
		}
		int moVal = this.currentPage % 5;
		this.startPage = this.currentPage/ 5 * 5+1;
		
		if(moVal ==0) {
			this.startPage -= 5;
		}
		if(endPage>totalPages) {
			endPage = totalPages;
		}
	}// end constructor
	}
	public int getTotal() {
		return total; 
	}
	public boolean hasNoMember() {
		return total ==0;
	}
	public boolean hasMember() {
		return total > 0;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public ArrayList<MemberVO> getMemberList(){
		return memberList;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
}
