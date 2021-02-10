package com.kyumin.calendar.calendarTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-context.xml")
public class passwordEncoderTest {
	
	@Autowired
	private ApplicationContext ac;
	@Autowired
	private MemberDTO dto;
	@Autowired
	private LoginDTO lto;
	@Autowired
	MemberService ms;
	
	@Before
	public void before() {
		System.out.println("======== 테스트 실행 ========");
	}
	
	@Test
	public void 암호화_구동확인() {

		BCryptPasswordEncoder pwEncoder = 
				(BCryptPasswordEncoder) ac.getBean("passwordEncoder");

		String pw = "4495";
		String encodedPw1 = pwEncoder.encode(pw);
		String encodedPw2 = pwEncoder.encode(pw);

		System.out.println("원본 : " + pw);
		System.out.println("첫번 째 인코딩 : " + encodedPw1);
		System.out.println("두번 째 인코딩 : " + encodedPw2);

		Assert.assertNotEquals(encodedPw1, encodedPw2);
	}
	
	@Test
	public void 회원가입_암호화() throws Exception {
		BCryptPasswordEncoder pwEncoder = 
				(BCryptPasswordEncoder) ac.getBean("passwordEncoder");
		

		dto.setLoginId("test1");
		dto.setLoginPw("4949");
		String encodedPw = pwEncoder.encode(dto.getLoginPw());
		dto.setLoginPw(encodedPw);
		dto.setName("테스트이름");
		dto.setEmail("메일");
		
		System.out.println("회원가입 암호화 : " + dto.toString());

		Assert.assertEquals(true, pwEncoder.matches("4949", dto.getLoginPw()));
	}
	
	@Test
	public void DB연동() throws Exception {
		BCryptPasswordEncoder pwEncoder = 
				(BCryptPasswordEncoder) ac.getBean("passwordEncoder");
		
		lto.setLoginId("cgm97");
		lto.setLoginPw("4495");
		String a = pwEncoder.encode(lto.getLoginPw());
		//LoginDTO result = ms.loginCheck(lto);
		//System.out.println(result.getLoginPw());
		Assert.assertEquals(true, pwEncoder.matches("4495", a));
	}
}
