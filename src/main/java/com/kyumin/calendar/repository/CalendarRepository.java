package com.kyumin.calendar.repository;

import java.util.List;
import com.kyumin.calendar.domain.CalendarDTO;

public interface CalendarRepository {
	int insertCalendar(CalendarDTO dto) throws Exception;
	int updateCalendar(CalendarDTO dto) throws Exception;
	int deleteCalender(int calendarNo) throws Exception;
	List<CalendarDTO> getCalendarListById(String id) throws Exception;
	CalendarDTO getCalendarInfoByCalendarNo(int calendarNo) throws Exception;
}
