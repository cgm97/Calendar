package com.kyumin.calendar.calendarTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-context.xml")
@Transactional
public class TransactionTest {
	
	@Autowired
	MemberService ms;
	
	@Test
	public void insertTest() throws Exception{
		MemberDTO dto = new MemberDTO();
		
		dto.setLoginId("test");
		dto.setLoginPw("1234");
		dto.setName("유닛");
		dto.setEmail("이메일");
		
		ms.createMember(dto);
	}
	
	@Test
	public void deleteTest() {
		String id = "test";
		
		int result = ms.deleteById(id);
		
		assertEquals(1, result);
	}
}
