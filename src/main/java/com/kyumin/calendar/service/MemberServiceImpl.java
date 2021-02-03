package com.kyumin.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyumin.calendar.domain.CalendarDTO;
import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.repository.CalendarRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private CalendarRepository dao;
	
	@Override
	public LoginDTO loginCheck(LoginDTO dto) throws Exception {
		return dao.memberCheckById(dto);
	}
	
}
