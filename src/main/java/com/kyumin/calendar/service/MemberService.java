package com.kyumin.calendar.service;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

public interface MemberService {
	int createMember(MemberDTO dto);
	int editMember(MemberDTO dto);
	int deleteById(String id);
	int idDuplication(String id);
	MemberDTO loginCheck(LoginDTO dto);
}
