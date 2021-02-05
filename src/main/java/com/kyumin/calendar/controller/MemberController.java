package com.kyumin.calendar.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	protected Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login_form() {
		return "login";	
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<Object,Object> login(@RequestBody LoginDTO dto, HttpSession session) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		logger.info("로그인 체크");
		LoginDTO getUserInfo = memberService.loginCheck(dto);
		
		if(getUserInfo.getName() != null) {
			logger.info("로그인 성공");
			session.setAttribute("loginedMemberId", getUserInfo.getLoginId());
			session.setAttribute("loginedMemberName", getUserInfo.getName());
			
			logger.info("마지막 로그인 날짜 업데이트");
			memberService.updateLastLogin(getUserInfo.getLoginId());
		}
		else {
			logger.info("로그인 실패");
			session.setAttribute("loginedMemberName","로그인 실패");
		}
		return map;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";	
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "join";	
	}
	
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(MemberDTO dto, HttpSession session) throws Exception {
		int result = memberService.createMember(dto);
		
		if(result != -1) {
			session.setAttribute("loginedMemberName","회원가입 성공");
		}
		else {
			session.setAttribute("loginedMemberName","회원가입 실패 - 아이디 중복");
		}
		
		return "redirect:/";
	}
}
