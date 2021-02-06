package com.kyumin.calendar.calendarTest;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-context.xml")
public class JdbcConnectionTest {     
	
	@Autowired
	private ApplicationContext ac;
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Test
	public  void Jdbc() {
			try {
				dataSource = (DataSource) ac.getBean("dataSource");
				Connection conn = dataSource.getConnection();
				
				System.out.print(conn);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

