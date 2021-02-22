package com.kyumin.calendar.calendarTest;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.service.MemberService;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/service-context.xml"})
public class ToServiceFromDaoTest {
	
	@Autowired
	MemberService memberService;
	
	@Test
	public void 적용_테스트() {
		LoginDTO dto = new LoginDTO();
		dto.setLoginId("mybatis");
		dto.setLoginPw("1234");
		
		MemberDTO getMember = memberService.loginCheck(dto);
		
		assertEquals("mybatis", getMember.getName());
	}
}
