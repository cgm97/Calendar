package com.kyumin.calendar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	private Map<Object,Object> map = new HashMap<Object,Object>();
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login_form() {
		return "login";	
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<Object,Object> login(@RequestBody LoginDTO dto, HttpSession session) throws Exception {
		LoginDTO getUserInfo = memberService.loginCheck(dto);
		
		if(getUserInfo.getName() != null) {
			session.setAttribute("loginedMemberId", getUserInfo.getLoginId());
			session.setAttribute("loginedMemberName", getUserInfo.getName());
		}
		else {
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
	@ResponseBody
	public MemberDTO join(MemberDTO dto) {
		return dto;	
	}
}
