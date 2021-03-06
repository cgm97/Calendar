package com.kyumin.calendar.domain;

public class CalendarDTO {
	private String loginId;
	private String startDate;
	private String title;
	private String endDate;
	private String content;
	private int calendarNo;
	
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCalendarNo() {
		return calendarNo;
	}
	public void setCalendarNo(int calendarNo) {
		this.calendarNo = calendarNo;
	}
	
	@Override
	public String toString() {
		return "CalendarDTO [startDate=" + startDate + ", title=" + title + ", endDate=" + endDate + ", content="
				+ content + ", calendarNo=" + calendarNo + "]";
	}
}
