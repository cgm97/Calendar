package com.kyumin.calendar.repository;

import java.util.List;
import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.domain.LoginDTO;

public interface CalendarRepository {
	void insertCalendar(CalendarDTO dto) throws Exception;
	void updateCalendar(CalendarDTO dto) throws Exception;
	void deleteCalender(int calendarNo) throws Exception;
	LoginDTO memberCheckById(LoginDTO dto) throws Exception;
	List<CalendarDTO> getCalendar(String getListById) throws Exception;
	CalendarDTO getCalendarByCalendarNo(int calendarNo) throws Exception;
}
