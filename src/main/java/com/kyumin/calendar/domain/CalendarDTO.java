package com.kyumin.calendar.domain;

import org.springframework.stereotype.Component;

@Component
public class CalendarDTO {
	private String startDate;
	private String title;
	private String endDate;
	private String content;
	
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
	@Override
	public String toString() {
		return "CalendarDTO [startDate=" + startDate + ", title=" + title + ", endDate=" + endDate + ", content="
				+ content + "]";
	}
	
}
