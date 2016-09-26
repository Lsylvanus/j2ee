package com.lsylvanus.model;


/**
 * ½èÔÄModel ¼Ì³ÐBaseModel
 */
public class BorrowBook extends BaseModel{

	private int studentId;
	private int bookId;
	private String borrowBeginTime;
	private String borrowEndtime;
	
	public int getBookId() {
		return bookId;
	}
	public String getBorrowBeginTime() {
		return borrowBeginTime;
	}
	public String getBorrowEndtime() {
		return borrowEndtime;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public void setBorrowBeginTime(String borrowBeginTime) {
		this.borrowBeginTime = borrowBeginTime;
	}
	public void setBorrowEndtime(String borrowEndtime) {
		this.borrowEndtime = borrowEndtime;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}
