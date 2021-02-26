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
	private static final String NAMESPACE = "calendarMapper.";
	
	@Override
	public int insertCalendar(CalendarDTO dto) throws Exception {
		return sqlSession.insert(NAMESPACE+"insertCalendar", dto);
	}

	@Override
	public int updateCalendar(CalendarDTO dto) throws Exception {
		return sqlSession.update(NAMESPACE+"updateCalendar", dto);
	}

	@Override
	public int deleteCalender(int calendarNo) throws Exception {
		return sqlSession.delete(NAMESPACE+"deleteCalendar", calendarNo);
	}

	@Override
	public List<CalendarDTO> getCalendarListById(String id) throws Exception {
		return sqlSession.selectList(NAMESPACE+"getCalendarList", id);
	}

	@Override
	public CalendarDTO getCalendarInfoByCalendarNo(int calendarNo) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getCalendarInfoByCalendarNo", calendarNo);
	}

}
