package com.kyumin.calendar.calendarTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.kyumin.calendar.domain.CalendarDTO;

public class JdbcConnectionTest {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";   
	private static final String USER = "C##CALENDAR";         
	private static final String PW = "4495";       
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ArrayList<CalendarDTO> list;
	@Test
	public  void Jdbc() {
			try {
				Class.forName(DRIVER);
				Connection conn = DriverManager.getConnection(URL, USER, PW);
				stmt = conn.createStatement();
				
				String sql = "select * from CALENDAR";
				rs = stmt.executeQuery(sql);
				list = new ArrayList<CalendarDTO>();
				String a = null;
				System.out.print(conn);
				while (rs.next()) {
					CalendarDTO dto = new CalendarDTO();
					dto.setTitle(rs.getString("TITLE"));
					dto.setStartDate(rs.getString("STARTDATE"));
					dto.setEndDate(rs.getString("ENDDATE"));
					dto.setContent(rs.getString("CONTENT"));
				}
				System.out.print(a);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

