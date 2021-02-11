package com.kyumin.calendar.calendarTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-context.xml")
public class TransactionTest {
	
	@Autowired
	MemberService ms;
	
	@Test
	public void transactionTest() throws Exception {
		MemberDTO dto = new MemberDTO();
		
		dto.setLoginId("junit234");
		dto.setLoginPw("1234");
		dto.setName("유닛");
		dto.setEmail("이메일");
		
		ms.createMember(dto);
	}
}
