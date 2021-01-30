package com.kyumin.calendar.service;

import java.text.ParseException;
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
		dao.insertCalendar(changeEndDate(dto,1)); // 추가를 위한 +1일
	}
	
	// 일정 목록 수정
	@Override
	public void editCalendar(CalendarDTO dto) throws Exception {
		dao.updateCalendar(changeEndDate(dto,1)); // 수정을 위한 +1일
	}
	
	// 일정 목록 불러오기
	@Override
	public List<CalendarDTO> showCalendar() throws Exception {
		return dao.getCalendar();
	}
	
	// 선택된 calendarNo 정보 불러오기
	@Override
	public CalendarDTO getListByCalendarNo(int calendarNo) throws Exception{
		CalendarDTO dto = dao.getCalendarByCalendarNo(calendarNo);
		
		return changeEndDate(dto,-1); // 조회를 위한 -1일
	}
	
    //2021-01-25 부터 27 까지로 선택하여 일정 추기시 화면상에 26 일까지로 표시 되는 문제를 해결하기 위해  +1일 또는 -1일 
	public CalendarDTO changeEndDate(CalendarDTO dto, int day) throws ParseException {
		Date before_endDate = change.parse(dto.getEndDate());
		
		cal.setTime(before_endDate);
		cal.add(Calendar.DATE, day);
		
		String after_endDate = change.format(cal.getTime());
		dto.setEndDate(after_endDate);
		
		return dto;
	}
}
