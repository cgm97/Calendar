package com.kyumin.calendar.repository.jdbc;

import java.util.List;
import com.kyumin.calendar.domain.CalendarDTO;

public interface CalendarRepository {
	void insertCalendar(CalendarDTO dto) throws Exception;
	void updateCalendar(CalendarDTO dto) throws Exception;
	void deleteCalender(int calendarNo) throws Exception;
	List<CalendarDTO> getCalendar(String getListById) throws Exception;
	CalendarDTO getCalendarByCalendarNo(int calendarNo) throws Exception;
}