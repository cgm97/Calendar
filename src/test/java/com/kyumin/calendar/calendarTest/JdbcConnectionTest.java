package com.kyumin.calendar.calendarTest;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcConnectionTest {     
	
	private DataSource dataSource;
	
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

	@Test
	public  void Jdbc() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:src/main/webapp/WEB-INF/spring/service-context.xml");
		dataSource = (DataSource) ac.getBean("dataSource");
			try {
				Connection conn = dataSource.getConnection();
				
				System.out.print(conn);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

