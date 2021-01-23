package com.kyumin.calendar.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyumin.calendar.domain.CalendarDTO;

@Controller
public class CalendarController {
	
	@GetMapping("/")
	public String Calendar() {
		return "calendar";
	}

	// 클릭된 날짜에 대한 일정 추가
	@RequestMapping(value="/calendarSelected", method=RequestMethod.GET)
	public String calendarSelected( @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date, Model model) {
		CalendarDTO dto = new CalendarDTO();
		SimpleDateFormat change = new SimpleDateFormat("yyyy-MM-dd");
		String start = change.format(date);
		
		dto.setStartDate(start);
		model.addAttribute("selectedCalendar",dto);
		return "addCalendar";
	}
	
	// 버튼에 대한 일정 추가
	@RequestMapping(value="/btnSelected", method=RequestMethod.GET)
	public String btnSelected() {
		return "addCalendar";
	}
	
	@RequestMapping(value="/addCalendar", method=RequestMethod.POST)
	@ResponseBody
	void add(@RequestBody CalendarDTO dto) {
		System.out.println("제목 : " + dto.getTitle());
		System.out.println("시작 : " + dto.getStartDate());
		System.out.println("종료 : " + dto.getEndDate());
		System.out.println("내용 : " + dto.getContent());
	}
}
