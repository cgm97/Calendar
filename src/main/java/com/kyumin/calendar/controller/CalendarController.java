package com.kyumin.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyumin.calendar.domain.CalendarDTO;

@Controller
public class CalendarController {
	
	@GetMapping("/")
	public String Calendar() {
		return "calendar";
	}
	
	// 클릭된 날짜에 대한 일정 추가
	@RequestMapping(value="/calendarSelected")
	public String calendarSelected( @RequestParam("year") String year,
						@RequestParam("month") String month,
						@RequestParam("day") String day, Model model) {

		CalendarDTO dto = new CalendarDTO();
		dto.setYear(year);
		dto.setMonth(month);
		dto.setDay(day);
		model.addAttribute("selectedCalendar",dto);
		return "addCalendar";
	}
	// 버튼에 대한 일정 추가
	@RequestMapping(value="/btnSelected")
	public String btnSelected() {
		return "addCalendar";
		}
}
