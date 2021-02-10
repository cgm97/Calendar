package com.kyumin.calendar.repository.jdbc.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kyumin.calendar.domain.CalendarDTO;

public class CalendarRowMapper implements RowMapper<CalendarDTO> {

	@Override
	public CalendarDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CalendarDTO dto = new CalendarDTO();
		dto.setTitle(rs.getString("TITLE"));
		dto.setStartDate(rs.getString("STARTDATE"));
		dto.setEndDate(rs.getString("ENDDATE"));
		dto.setContent(rs.getString("CONTENT"));
		dto.setCalendarNo(rs.getInt("CALENDARNO"));
		return dto;
	}

}
