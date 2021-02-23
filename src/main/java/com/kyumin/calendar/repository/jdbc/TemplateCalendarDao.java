package com.kyumin.calendar.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.repository.CalendarRepository;
import com.kyumin.calendar.repository.jdbc.mapper.CalendarRowMapper;

@Repository
//@Primary
public class TemplateCalendarDao implements CalendarRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertCalendar(CalendarDTO dto) throws Exception {
		String sql = "INSERT INTO CALENDAR (CALENDARNO, LOGINID, TITLE, STARTDATE , ENDDATE , CONTENT) "
				+ "VALUES(CALENDARID.NEXTVAL,?,?,?,?,?)";
		
		return jdbcTemplate.update(sql,dto.getLoginId(),dto.getTitle(),dto.getStartDate(),dto.getEndDate(),dto.getContent());
	}

	@Override
	public int updateCalendar(CalendarDTO dto) throws Exception {
		String sql = "UPDATE CALENDAR SET TITLE=?, STARTDATE=?, ENDDATE=?, CONTENT=? WHERE CALENDARNO=?";
		
		return jdbcTemplate.update(sql,dto.getTitle(),dto.getStartDate(),dto.getEndDate(),dto.getContent(),dto.getCalendarNo());
	}

	@Override
	public int deleteCalender(int calendarNo) throws Exception {
		String sql = "DELETE FROM CALENDAR WHERE CALENDARNO=?";
		
		return jdbcTemplate.update(sql, calendarNo);
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
