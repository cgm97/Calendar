package com.kyumin.calendar.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
//	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<CalendarDTO> calendarList;
	
	private void connectDB() throws ClassNotFoundException,SQLException{
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, USER, PW);
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
	}
	
	@Override
	public void insertCalendar(CalendarDTO dto) {
		// TODO Auto-generated method stub

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
				calendarList.add(dto);
			}
				rs.close();
				disconnectDB();

		return calendarList;
	}

}
