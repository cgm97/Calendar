package com.kyumin.calendar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.repository.jdbc.MemberRepository;
import com.kyumin.calendar.service.MemberService;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository dao;
	@Autowired
	private MemberDTO mdto;
	
	@Override
	public MemberDTO loginCheck(LoginDTO dto) throws Exception {
		String id = dao.memberCheckById(dto); //로그인 체크
		dao.updateLastLogin(dto.getLoginId()); // 로그인기록
		mdto = dao.getMemberById(id); // 해당 멤버 정보 가져오기
		return mdto;
	}

	@Override
	public int createMember(MemberDTO dto) throws Exception {
		return dao.memberInsert(dto);
	}

	@Override
	public int idDuplication(String iD) {
		// TODO Auto-generated method stub
		return dao.idDupCheck(iD);
	}
	
}
