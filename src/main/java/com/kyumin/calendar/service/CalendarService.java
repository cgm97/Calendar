package com.kyumin.calendar.service;

import java.util.Date;

import org.springframework.ui.Model;

public interface CalendarService {
	void clickDate(Date selectedDate, Model model);
}
