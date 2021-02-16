package com.kyumin.calendar.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		MemberDTO getUserInfo = memberService.loginCheck(dto);
		
		if(getUserInfo.getName() != null) {
			logger.info("로그인 성공");
			session.setAttribute("loginedMember", getUserInfo);
			session.setAttribute("loginedMemberId", getUserInfo.getLoginId());
			session.removeAttribute("result");
		}
		else {
			logger.info("로그인 실패");
			session.setAttribute("result","로그인 실패");
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
		
		if(result > 0) {
			session.setAttribute("result","회원가입 성공");
		}
		else {
			session.setAttribute("result","회원가입 실패 - 아이디 중복");
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage() {	
		return "mypage";	
	}
	
	@RequestMapping(value="/mypage.do", method=RequestMethod.POST)
	public String mypage(MemberDTO dto, Model model, HttpSession session) {	
		logger.info(dto.toString());
		int result = memberService.editMember(dto);

		if(result == 1) {
			session.setAttribute("loginedMember", dto);
		}else {
			result=-1;
		}
		model.addAttribute("result", result);
		
		return "mypage";	
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(MemberDTO dto, HttpSession session) {
		logger.info(dto.getLoginId()+" 삭제 시작");
		int result = memberService.deleteById(dto.getLoginId());
		
		if(result == 1) {
			session.setAttribute("result", dto.getLoginId()+" -> 해당 유저 아이디 삭제 완료");
			session.removeAttribute("loginedMember");
			session.removeAttribute("loginedMemberId");
		}
		else {
			session.setAttribute("result", "삭제 실패");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/duplication", method=RequestMethod.POST)
	@ResponseBody
	public String dulpliCheck(@RequestBody String check_id) {
		String ID = check_id.trim();
		int id_dupCheck = memberService.idDuplication(ID);
		
		if(id_dupCheck == 0) {
			return "success";	// 사용 가능		
		}else {
			return "failed";	
		}
	}
}
