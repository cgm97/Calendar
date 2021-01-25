package com.kyumin.calendar.repository;

import org.springframework.ui.Model;

import com.kyumin.calendar.domain.CalendarDTO;

public interface CalendarRepository {
	void insertCalendar(CalendarDTO dto);
}
