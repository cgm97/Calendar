package com.kyumin.calendar.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.service.CalendarService;

@Controller
public class CalendarController {
	
	@Autowired
	private CalendarService calendarService;
	private Map<Object,Object> map = new HashMap<Object,Object>();
	
	@GetMapping("/")
	public String Calendar(Model model) throws Exception {
		// 나의 일정 가져오기
		model.addAttribute("getList",calendarService.showCalendar("cgm97"));

		return "calendar";
	}

	// 클릭된 날짜에 대한 일정 추가 팝업
	@RequestMapping(value="/calendarSelected", method=RequestMethod.GET)
	public String calendarSelected( @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date selectedDate, Model model) {
		calendarService.clickDate(selectedDate, model);

		return "addCalendar";
	}
	
	// 버튼에 대한 일정 추가 팝업
	@RequestMapping(value="/btnSelected", method=RequestMethod.GET)
	public String btnSelected() {
		return "addCalendar";
	}
	
	// 일정 수정
	@RequestMapping(value="calendarUpdate", method=RequestMethod.GET)
	public String calendarUpdate( @RequestParam("no") int calendarNo, Model model) throws Exception{
		model.addAttribute("getListByNo",calendarService.getListByCalendarNo(calendarNo));
 		return "editCalendar";	
	}
	
	// ajax 통신 - 일정 추가
	@RequestMapping(value="/addCalendar", method=RequestMethod.POST)
	@ResponseBody
	public Map<Object,Object> add(@RequestBody CalendarDTO dto) throws Exception{
		calendarService.writeCalendar(dto);

		return map;
	}
	
	// ajax 통신 - 일정 수정
	@RequestMapping(value="/editCalendar", method=RequestMethod.POST)
	@ResponseBody
	public Map<Object,Object> edit(@RequestBody CalendarDTO dto) throws Exception{
		calendarService.editCalendar(dto);

		return map;
	}
	
	// ajax 통신 - 일정 삭제
	@RequestMapping(value="/deleteCalendar", method=RequestMethod.POST)
	@ResponseBody
	public void delete(@RequestBody int calendarNo) throws Exception{
		calendarService.deleteCalender(calendarNo);
	}
}