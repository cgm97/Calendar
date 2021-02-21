package com.kyumin.calendar.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.service.CalendarService;

@Controller
public class CalendarController {
	
	@Autowired
	private CalendarService calendarService;
	
	@GetMapping("/")
	public String Calendar(Model model, HttpSession session) throws Exception {
		MemberDTO memberInfo = (MemberDTO) session.getAttribute("loginedMember");
		String getCalendarById = "";
		if(memberInfo != null) { // 처음 실행시 session 값이 없어 null point 오류 발생
			getCalendarById = memberInfo.getLoginId();		
		}		
		// 나의 일정 가져오기
		model.addAttribute("getList",calendarService.showCalendar(getCalendarById));

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
	
	// 일정 수정 페이지 - 선택된 값 가져오기
	@RequestMapping(value="/calendarUpdate", method=RequestMethod.GET)
	public String calendarUpdate( @RequestParam("no") int calendarNo, Model model) throws Exception{
		model.addAttribute("getListByNo",calendarService.getListByCalendarNo(calendarNo));
 		return "editCalendar";	
	}
	
	// ajax 통신 - 일정 추가
	@RequestMapping(value="/addCalendar", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(@RequestBody CalendarDTO dto, HttpSession session) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginedMember");
		String loginId = memberDTO.getLoginId();
		dto.setLoginId(loginId);	
		
		int result = calendarService.writeCalendar(dto);
		
		if(result > 0) {
			map.put("key","success");
			map.put("msg","일정 추가 성공");
		}else {
			map.put("key","failed");
			map.put("msg","일정 추가 실패");
		}
		return map;
	}
	
	// ajax 통신 - 일정 수정
	@RequestMapping(value="/editCalendar", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> edit(@RequestBody CalendarDTO dto) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		int result = calendarService.editCalendar(dto);
		
		if(result > 0) {
			map.put("key","success");
			map.put("msg","일정 수정 성공");
		}else {
			map.put("key","failed");
			map.put("msg","일정 수정 실패");
		}	
		return map;
	}
	
	// ajax 통신 - 일정 삭제
	@RequestMapping(value="/deleteCalendar", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(@RequestBody int calendarNo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		int result = calendarService.deleteCalender(calendarNo);
		
		if(result > 0) {
			map.put("key","success");
			map.put("msg","일정 삭제 성공");
		}else {
			map.put("key","failed");
			map.put("msg","일정 삭제 실패");
		}
		return map;
	}
}