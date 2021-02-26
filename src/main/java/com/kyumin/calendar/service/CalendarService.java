package com.kyumin.calendar.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.ui.Model;
import com.kyumin.calendar.domain.CalendarDTO;

public interface CalendarService {
	int writeCalendar(CalendarDTO dto) throws Exception;
	int editCalendar(CalendarDTO dto) throws Exception;
	int deleteCalender(int calendarNo) throws Exception;
	void clickDate(LocalDate selectedDate, Model model);
	List<CalendarDTO> showCalendar(String id) throws Exception;
	CalendarDTO getCalendarInfoByCalendarNo(int calendarNo) throws Exception;
}
