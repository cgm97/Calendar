package com.kyumin.calendar.repository;



import com.kyumin.calendar.domain.CalendarDTO;

public interface CalendarRepository {
	void insertCalendar(CalendarDTO dto);
}
