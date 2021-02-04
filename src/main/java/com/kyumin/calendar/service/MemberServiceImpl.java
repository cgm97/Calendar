package com.kyumin.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository dao;
	
	@Override
	public LoginDTO loginCheck(LoginDTO dto) throws Exception {
		return dao.memberCheckById(dto);
	}

	@Override
	public int createMember(MemberDTO dto) throws Exception {
		return dao.memberInsert(dto);
	}
	
}
