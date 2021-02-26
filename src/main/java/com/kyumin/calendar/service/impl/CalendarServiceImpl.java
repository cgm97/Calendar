package com.kyumin.calendar.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.repository.CalendarRepository;
import com.kyumin.calendar.service.CalendarService;

@Service
@Transactional
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private CalendarRepository dao;

	private DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	/*
	 * private SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
	 * private Calendar cal = Calendar.getInstance();
	 */
	
	@Override
	public void clickDate(LocalDate selectedDate, Model model) {
		CalendarDTO calendarDTO = new CalendarDTO();

		String startDate = selectedDate.format(dtFormatter);
		
		calendarDTO.setStartDate(startDate);
		model.addAttribute("selectedCalendar", calendarDTO);
	}

	// 일정 목록 추가
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public int writeCalendar(CalendarDTO dto) throws Exception {
		return dao.insertCalendar(changeEndDate(dto, 1)); // 추가를 위한 +1일
	}

	// 일정 목록 수정
	@Override
	public int editCalendar(CalendarDTO dto) throws Exception {
		return dao.updateCalendar(changeEndDate(dto, 1)); // 수정을 위한 +1일
	}

	// 일정 삭제
	@Override
	public int deleteCalender(int calendarNo) throws Exception {
		return dao.deleteCalender(calendarNo);
	}

	// 일정 목록 불러오기
	@Override
	public List<CalendarDTO> showCalendar(String id) throws Exception {
		return dao.getCalendarListById(id);
	}

	// 선택된 calendarNo 정보 불러오기
	@Override
	public CalendarDTO getCalendarInfoByCalendarNo(int calendarNo) throws Exception {
		CalendarDTO dto = dao.getCalendarInfoByCalendarNo(calendarNo);

		return changeEndDate(dto, -1); // 조회를 위한 -1일
	}

	// 2021-01-25 부터 27 까지로 선택하여 일정 추기시 화면상에 26 일까지로 표시 되는 문제를 해결하기 위해 +1일 또는 -1일
	public CalendarDTO changeEndDate(CalendarDTO calendar, int day) throws ParseException {
		/* -> Date 관련 처리
		 * Date before_endDate = change.parse(dto.getEndDate());
		 * 
		 * cal.setTime(before_endDate); cal.add(Calendar.DATE, day);
		 * 
		 * String after_endDate = change.format(cal.getTime());
		 * dto.setEndDate(after_endDate);
		 *
		-> LocalDate 관련 처리 - Java 8 이상 지원
		 */
		LocalDate before_endDate = LocalDate.parse(calendar.getEndDate());	
		String after_endDate = before_endDate.plusDays(day)
											.format(dtFormatter);
		calendar.setEndDate(after_endDate);
		
		return calendar;
	}
}
