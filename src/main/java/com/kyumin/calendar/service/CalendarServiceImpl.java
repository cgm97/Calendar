package com.kyumin.calendar.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.repository.CalendarRepository;

@Service
public class CalendarServiceImpl implements CalendarService {
	
	@Autowired
	private CalendarDTO calendarDTO;
	@Autowired
	private CalendarRepository dao;
	
	private SimpleDateFormat change = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar cal = Calendar.getInstance();
	
	@Override
	public void clickDate(Date selectedDate, Model model) {
		String startDate = change.format(selectedDate);

		calendarDTO.setStartDate(startDate);
		model.addAttribute("selectedCalendar", calendarDTO);
	}
	
	// 일정 목록 추가
	@Override
	public void writeCalendar(CalendarDTO dto) throws Exception{
		/*
       * 2021-01-25 부터 27 까지로 선택하여 일정 추기시 화면상에 26 일까지로 표시 되는 문제를 해결하기 위해  +1일
		 */
		cal = Calendar.getInstance();
		Date before_endDate = change.parse(dto.getEndDate());
		
		cal.setTime(before_endDate);
		cal.add(Calendar.DATE, 1);
		String after_endDate = change.format(cal.getTime());
		
		dto.setEndDate(after_endDate);
		dao.insertCalendar(dto);
	}
	
	// 일정 목록 불러오기
	@Override
	public List<CalendarDTO> showCalendar() throws Exception {
		return dao.getCalendar();
	}
}
