package com.kyumin.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

@Controller
public class MemberController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login_form() {
		return "login";	
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	@ResponseBody
	public LoginDTO login(LoginDTO dto) {
		return dto;	
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
