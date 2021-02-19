package com.kyumin.calendar.calendarTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/service-context.xml"})
public class myBatisTest {
	@Autowired
	private DataSource ds;
	@Autowired
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void 통신테스트() throws SQLException {
		try(Connection conn = ds.getConnection()) {
			assertNotEquals(null, conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void sql테스트() {
		assertNotEquals(null, sqlFactory);
	}
	
	@Test
	public void session테스트() {
		try(SqlSession session = sqlFactory.openSession()) {
			assertNotEquals(null, session);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
