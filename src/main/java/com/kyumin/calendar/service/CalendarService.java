package com.kyumin.calendar.service;

import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;

import com.kyumin.calendar.domain.CalendarDTO;

public interface CalendarService {
	int writeCalendar(CalendarDTO dto) throws Exception;
	int editCalendar(CalendarDTO dto) throws Exception;
	int deleteCalender(int calendarNo) throws Exception;
	void clickDate(Date selectedDate, Model model);
	List<CalendarDTO> showCalendar(String getListById) throws Exception;
	CalendarDTO getListByCalendarNo(int calendarNo) throws Exception;
}
