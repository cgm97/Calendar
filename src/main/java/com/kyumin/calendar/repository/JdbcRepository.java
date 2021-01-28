package com.kyumin.calendar.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyumin.calendar.domain.CalendarDTO;

@Repository
public class JdbcRepository implements CalendarRepository {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";   
	private static final String USER = "C##CALENDAR";         
	private static final String PW = "4495";  	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<CalendarDTO> calendarList = null;
//	@Autowired
//	private DataSource dataSource;
//
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	
	private void connectDB() throws ClassNotFoundException,SQLException{
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, USER, PW);
//		conn = dataSource.getConnection();
		if(conn != null)
		{
			stmt = conn.createStatement();
		}
	}
	private void disconnectDB() throws SQLException {
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}	
		if (conn != null) {
			conn.close();
			conn = null;
		}	
		if (pstmt != null) {
			pstmt.close();
			pstmt = null;
		}
	}
	
	@Override
	public void insertCalendar(CalendarDTO dto) throws Exception{
		
		String sql = "INSERT INTO CALENDAR (CALENDARNO,TITLE, STARTDATE , ENDDATE , CONTENT) "
						+ "VALUES(CALENDARID.NEXTVAL,?,?,?,?)";
		connectDB();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getTitle());
		pstmt.setString(2, dto.getStartDate());
		pstmt.setString(3, dto.getEndDate());
		pstmt.setString(4, dto.getContent());
		
		pstmt.executeUpdate();
		disconnectDB();
	}

	@Override
	public List<CalendarDTO> getCalendar() throws Exception {
		String sql = "select * from CALENDAR";
		connectDB();
		rs = stmt.executeQuery(sql);
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
		disconnectDB();

		return calendarList;
	}
	
	@Override
	public CalendarDTO getCalendarByCalendarNo(int calendarNo) throws Exception {
		String sql = "select * from CALENDAR WHERE CALENDARNO=?";
		connectDB();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, calendarNo);
		rs = pstmt.executeQuery();
		CalendarDTO dto = new CalendarDTO();
		
		while(rs.next()) {
			dto.setTitle(rs.getString("TITLE"));
			dto.setStartDate(rs.getString("STARTDATE"));
			dto.setEndDate(rs.getString("ENDDATE"));
			dto.setContent(rs.getString("CONTENT"));
		}
		rs.close();
		disconnectDB();
		return dto;
	}

}
