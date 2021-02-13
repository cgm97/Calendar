package com.kyumin.calendar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.repository.MemberRepository;
import com.kyumin.calendar.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository dao;
	private MemberDTO mdto;
	
	@Override
	public MemberDTO loginCheck(LoginDTO dto) throws Exception {
		mdto = new MemberDTO();
		String id = dao.memberCheckById(dto); //로그인 체크
		
		if(id != null) {
			dao.updateLastLogin(dto.getLoginId()); // 로그인기록
			mdto = dao.getMemberById(id); // 해당 멤버 정보 가져오기			
		}
		return mdto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public int createMember(MemberDTO dto) throws Exception{	
		return dao.memberInsert(dto);
	}

	@Override
	public int idDuplication(String iD) {
		// TODO Auto-generated method stub
		return dao.idDupCheck(iD);
	}

	@Override
	public int editMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		return dao.memberUpdate(dto);
	}
	
}
