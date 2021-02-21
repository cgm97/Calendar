package com.kyumin.calendar.calendarTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/service-context.xml"})
public class myBatisTest {
	@Autowired
	private DataSource ds;
	@Autowired
	private SqlSessionFactory sqlFactory;
	private final Logger Logger = LoggerFactory.getLogger(myBatisTest.class);
	
	@Test
	public void 통신테스트() throws SQLException {
		try(Connection conn = ds.getConnection()) {
			assertNotEquals(null, conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void sqlFactory테스트() {
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
	
	@Test
	public void 회원가입() {
		SqlSession sqlSession = sqlFactory.openSession();
		MemberDTO member = new MemberDTO();
		member.setLoginId("mybatis");
		member.setLoginPw("1234");
		member.setName("mybatis");
		member.setEmail("test.com");
		sqlSession.insert("memberMapper.memberInsert", member);
	}
	
	@Test
	public void 로그인() {
		SqlSession sqlSession = sqlFactory.openSession();
		LoginDTO login = new LoginDTO();
		login.setLoginId("mybatis");
		login.setLoginPw("1234");
		String loginedId = sqlSession.selectOne("memberMapper.checkLogin", login);
		assertEquals("mybatis", loginedId);
	}
	
	@Test
	public void 유저정보ById() {
		SqlSession sqlSession = sqlFactory.openSession();
		String id = "mybatis";
		List<MemberDTO> member = sqlSession.selectList("memberMapper.getMemberById", id);
		Logger.info("member {}",member);
		assertNotEquals(null, member);
	}
	
	@Test
	public void 캘린더리스트ById() {
		SqlSession sqlSession = sqlFactory.openSession();
		String id = "cgm97";
		List<CalendarDTO> calendarList = sqlSession.selectList("calendarMapper.getCalendar", id);
		Logger.info("calendarList : {}",calendarList);
		assertNotEquals(null, calendarList);
	}
	
	@Test
	public void 유저정보수정() {
		SqlSession sqlSession = sqlFactory.openSession();
		MemberDTO member = new MemberDTO();
		member.setLoginId("mybatis");
		member.setLoginPw("1234");
		member.setName("수정 테스트완료");
		member.setEmail("test.com");
		int result = sqlSession.update("memberMapper.memberUpdate", member);
		assertEquals(1, result);
	}
	
	@Test
	public void 유저정보삭제() {
		SqlSession sqlSession = sqlFactory.openSession();
		String id = "mybatis";
		int result = sqlSession.delete("memberMapper.deleteById",id);
		assertEquals(1, result);
	}
}
