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
	public Map<String, Object> login(@RequestBody LoginDTO dto, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("로그인 체크");
		MemberDTO getUserInfo = memberService.loginCheck(dto);
		
		if(getUserInfo.getName() != null) {
			logger.info("로그인 성공");
			session.setAttribute("loginedMember", getUserInfo);
			session.setAttribute("loginedMemberId", getUserInfo.getLoginId());
			map.put("key","success");
			map.put("msg", "로그인 성공");
		}
		else {
			logger.info("로그인 실패");
			map.put("key","failed");
			map.put("msg", "로그인 실패 - ID or PW가 맞지 않습니다.");
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
	public String join(MemberDTO dto, Model model) throws Exception {
		int result = memberService.createMember(dto);
		
		if(result > 0) {
			model.addAttribute("msg","회원가입 성공");
			model.addAttribute("url","/calendar");
		}
		else {
			model.addAttribute("msg","회원가입 실패 - 아이디 중복");
			model.addAttribute("url","/calendar/join");
		}		
		return "common/alert";
	}
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage() {	
		return "mypage";	
	}
	
	@RequestMapping(value="/mypage.do", method=RequestMethod.POST)
	public String mypage(MemberDTO dto, Model model, HttpSession session) {	
		logger.info(dto.toString());
		int result = memberService.editMember(dto);

		if(result > 0) { // 현재 세션에 가지고 있는 정보도 함께 업데이트 
			session.setAttribute("loginedMember", dto);
			model.addAttribute("msg","수정 완료");
		}else {
			model.addAttribute("msg","수정 실패");
		}
		model.addAttribute("url","/calendar/mypage");
		
		return "common/alert";	
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(MemberDTO dto, HttpSession session, Model model) {
		logger.info(dto.getLoginId()+" 삭제 시작");
		int result = memberService.deleteById(dto.getLoginId());
		
		if(result > 0) {
			session.removeAttribute("loginedMember");
			session.removeAttribute("loginedMemberId");
			model.addAttribute("msg","계정 삭제 완료");
			model.addAttribute("url","/calendar");
		}
		else {
			model.addAttribute("msg","계정 삭제 실패");
			model.addAttribute("url","/calendar/mypage");
		}		
		return "common/alert";
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
