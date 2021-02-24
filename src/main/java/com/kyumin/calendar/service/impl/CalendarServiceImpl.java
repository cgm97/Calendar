package com.kyumin.calendar.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

	DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
//	private Calendar cal = Calendar.getInstance();
	
	@Override
	public void clickDate(Date selectedDate, Model model) {
		CalendarDTO calendarDTO = new CalendarDTO();

		String startDate = sdFormatter.format(selectedDate);
		
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
	public List<CalendarDTO> showCalendar(String getListById) throws Exception {
		return dao.getCalendar(getListById);
	}

	// 선택된 calendarNo 정보 불러오기
	@Override
	public CalendarDTO getListByCalendarNo(int calendarNo) throws Exception {
		CalendarDTO dto = dao.getCalendarByCalendarNo(calendarNo);

		return changeEndDate(dto, -1); // 조회를 위한 -1일
	}

	// 2021-01-25 부터 27 까지로 선택하여 일정 추기시 화면상에 26 일까지로 표시 되는 문제를 해결하기 위해 +1일 또는 -1일
	public CalendarDTO changeEndDate(CalendarDTO dto, int day) throws ParseException {
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
		LocalDate before_endDate = LocalDate.parse(dto.getEndDate());	
		String after_endDate = before_endDate.plusDays(day)
								             .format(dtFormatter);
		dto.setEndDate(after_endDate);
		
		return dto;
	}
}
