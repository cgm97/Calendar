package com.kyumin.calendar.repository.jdbc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.repository.jdbc.CalendarRepository;

@Repository
@Primary
public class TemplateCalendarDao implements CalendarRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertCalendar(CalendarDTO dto) throws Exception {
		String sql = "INSERT INTO CALENDAR (CALENDARNO, LOGINID, TITLE, STARTDATE , ENDDATE , CONTENT) "
				+ "VALUES(CALENDARID.NEXTVAL,?,?,?,?,?)";
		
		jdbcTemplate.update(sql,dto.getLoginId(),dto.getTitle(),dto.getStartDate(),dto.getEndDate(),dto.getContent());
	}

	@Override
	public void updateCalendar(CalendarDTO dto) throws Exception {
		String sql = "UPDATE CALENDAR SET TITLE=?, STARTDATE=?, ENDDATE=?, CONTENT=? WHERE CALENDARNO=?";
		
		jdbcTemplate.update(sql,dto.getTitle(),dto.getStartDate(),dto.getEndDate(),dto.getContent(),dto.getCalendarNo());
	}

	@Override
	public void deleteCalender(int calendarNo) throws Exception {
		String sql = "DELETE FROM CALENDAR WHERE CALENDARNO=?";
		
		jdbcTemplate.update(sql, calendarNo);
	}

	@Override
	public List<CalendarDTO> getCalendar(String getListById) throws Exception {
		String sql = "select * from CALENDAR WHERE LOGINID=?";
		
		return jdbcTemplate.query(sql, new CalendarRowMapper(), getListById);
	}

	@Override
	public CalendarDTO getCalendarByCalendarNo(int calendarNo) throws Exception {
		String sql = "select * from CALENDAR WHERE CALENDARNO=?";
		
		return jdbcTemplate.queryForObject(sql, new CalendarRowMapper(), calendarNo);
	}

}
