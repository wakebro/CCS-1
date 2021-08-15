package com.yjw.board.model;

import java.sql.Timestamp;

public class BoardVO {
	
	private int textNum;
	private String writer_id;
	private String title;
	private String content;
	private Timestamp date;
	private int view;
	
	
	public BoardVO() {
		
	}
	
	public BoardVO(int textNum, String writer_id, String title, String content, Timestamp date, int view) {
		super();
		this.textNum = textNum;
		this.writer_id = writer_id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.view = view;
	}

	
	@Override
	public String toString() {
		return "BoardVO [textNum=" + textNum + ", writer_id=" + writer_id + ", title=" + title + ", content=" + content
				+ ", date=" + date + ", view=" + view + "]";
	}

	public int getTextNum() {
		return textNum;
	}

	public void setTextNum(int textNum) {
		this.textNum = textNum;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}
	
}
