package com.kyumin.calendar.repository.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.repository.CalendarRepository;

@Repository
@Primary
public class MybatisCalendarDao implements CalendarRepository {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "calendarMapper.";
	
	@Override
	public int insertCalendar(CalendarDTO dto) throws Exception {
		return sqlSession.insert(namespace+"insertCalendar", dto);
	}

	@Override
	public int updateCalendar(CalendarDTO dto) throws Exception {
		return sqlSession.update(namespace+"updateCalendar", dto);
	}

	@Override
	public int deleteCalender(int calendarNo) throws Exception {
		return sqlSession.delete(namespace+"deleteCalendar", calendarNo);
	}

	@Override
	public List<CalendarDTO> getCalendar(String getListById) throws Exception {
		return sqlSession.selectList(namespace+"getCalendar", getListById);
	}

	@Override
	public CalendarDTO getCalendarByCalendarNo(int calendarNo) throws Exception {
		return sqlSession.selectOne(namespace+"getCalendarByCalendarNo", calendarNo);
	}

}
