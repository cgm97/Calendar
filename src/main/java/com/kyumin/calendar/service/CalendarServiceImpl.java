package com.kyumin.calendar.service;

import java.text.SimpleDateFormat;
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

	@Override
	public void clickDate(Date selectedDate, Model model) {
		SimpleDateFormat change = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = change.format(selectedDate);

		calendarDTO.setStartDate(startDate);
		model.addAttribute("selectedCalendar", calendarDTO);
	}

	@Override
	public void writeCalendar(CalendarDTO dto) {
		System.out.println("제목 : " + dto.getTitle());
		System.out.println("시작 : " + dto.getStartDate());
		System.out.println("종료 : " + dto.getEndDate());
		System.out.println("내용 : " + dto.getContent());
		
		dao.insertCalendar(dto);
	}

	@Override
	public List<CalendarDTO> showCalendar() throws Exception {
		/*
		 * List<CalendarDTO> list = new ArrayList<CalendarDTO>();
		 * calendarDTO.setTitle("aaa"); calendarDTO.setStartDate("2021-01-25");
		 * list.add(calendarDTO); // -> dao 로 이동 해야함
		 */
	
		return dao.getCalendar();
	}
}
