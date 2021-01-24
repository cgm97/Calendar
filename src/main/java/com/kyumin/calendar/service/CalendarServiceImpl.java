package com.kyumin.calendar.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kyumin.calendar.domain.CalendarDTO;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService{
	
	private CalendarDTO calendarDTO;
	
	@Autowired
	public void setCalendarDTO(CalendarDTO calendarDTO) {
		this.calendarDTO = calendarDTO;
	}

	@Override
	public void clickDate(Date selectedDate, Model model) {
		SimpleDateFormat change = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = change.format(selectedDate);
		
		calendarDTO.setStartDate(startDate);
		model.addAttribute("selectedCalendar",calendarDTO);
	}
}
