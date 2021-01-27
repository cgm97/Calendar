package com.kyumin.calendar.service;

import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;

import com.kyumin.calendar.domain.CalendarDTO;

public interface CalendarService {
	void clickDate(Date selectedDate, Model model);
	void writeCalendar(CalendarDTO dto);
	List<CalendarDTO> showCalendar() throws Exception;
}
