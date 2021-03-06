package com.kyumin.calendar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	@Override
	public MemberDTO loginCheck(LoginDTO dto) {
		MemberDTO mdto = new MemberDTO();
		String id = dao.memberCheckById(dto); //로그인 체크
		
		if (id != null) {
			dao.updateLastLogin(dto.getLoginId()); // 로그인기록
			mdto = dao.getMemberById(id); // 해당 멤버 정보 가져오기			
		}
		return mdto;
	}
	
	@Override
	public int createMember(MemberDTO dto){	
		return dao.memberInsert(dto);
	}

	@Override
	public int idDuplication(String id) {
		return dao.duplicateId(id);
	}

	@Override
	public int editMember(MemberDTO dto) {
		return dao.memberUpdate(dto);
	}

	@Override
	public int deleteById(String id) {
		return dao.deleteById(id);
	}
	
}
