package com.kyumin.calendar.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.kyumin.calendar.common.JdbcUtil;
import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.repository.CalendarRepository;

@Repository
//@Primary
public class JdbcCalendarDao implements CalendarRepository {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<CalendarDTO> calendarList = null;
	private int result = -1;
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Override
	public int insertCalendar(CalendarDTO dto) throws Exception{	
		String sql = "INSERT INTO CALENDAR (CALENDARNO, LOGINID, TITLE, STARTDATE , ENDDATE , CONTENT) "
						+ "VALUES(CALENDARID.NEXTVAL,?,?,?,?,?)";

		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getLoginId());
		pstmt.setString(2, dto.getTitle());
		pstmt.setString(3, dto.getStartDate());
		pstmt.setString(4, dto.getEndDate());
		pstmt.setString(5, dto.getContent());
		
		result = pstmt.executeUpdate();
		JdbcUtil.close(pstmt, conn);
		
		return result;
	}
	
	@Override
	public int updateCalendar(CalendarDTO dto) throws Exception {
		String sql = "UPDATE CALENDAR SET TITLE=?, STARTDATE=?, ENDDATE=?, CONTENT=? WHERE CALENDARNO=?";

		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getTitle());
		pstmt.setString(2, dto.getStartDate());
		pstmt.setString(3, dto.getEndDate());
		pstmt.setString(4, dto.getContent());
		pstmt.setInt(5, dto.getCalendarNo());
		
		result = pstmt.executeUpdate();
		JdbcUtil.close(pstmt, conn);
		
		return result;
	}
	
	@Override
	public int deleteCalender(int calendarNo) throws Exception {
		String sql = "DELETE FROM CALENDAR WHERE CALENDARNO=?";
		
		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, calendarNo);
		
		result = pstmt.executeUpdate();
		JdbcUtil.close(pstmt, conn);
		
		return result;
	}

	@Override
	public List<CalendarDTO> getCalendarListById(String id) throws Exception {
		String sql = "select * from CALENDAR WHERE LOGINID=?";
		conn = dataSource.getConnection();
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		calendarList = new ArrayList<CalendarDTO>();
		while(rs.next()) {
			CalendarDTO dto = new CalendarDTO();
			dto.setTitle(rs.getString("TITLE"));
			dto.setStartDate(rs.getString("STARTDATE"));
			dto.setEndDate(rs.getString("ENDDATE"));
			dto.setContent(rs.getString("CONTENT"));
			dto.setCalendarNo(rs.getInt("CALENDARNO"));

			calendarList.add(dto);
		}
		rs.close();
		JdbcUtil.close(rs, pstmt, conn);

		return calendarList;
	}
	
	@Override
	public CalendarDTO getCalendarInfoByCalendarNo(int calendarNo) throws Exception {
		String sql = "select * from CALENDAR WHERE CALENDARNO=?";
		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, calendarNo);
		rs = pstmt.executeQuery();
		CalendarDTO dto = new CalendarDTO();
		
		if(rs.next()) {
			dto.setTitle(rs.getString("TITLE"));
			dto.setStartDate(rs.getString("STARTDATE"));
			dto.setEndDate(rs.getString("ENDDATE"));
			dto.setContent(rs.getString("CONTENT"));
			dto.setCalendarNo(rs.getInt("CALENDARNO"));
		}
		rs.close();
		JdbcUtil.close(rs, pstmt, conn);
		
		return dto;
	}
}
