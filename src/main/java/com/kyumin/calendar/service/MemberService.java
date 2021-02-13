package com.kyumin.calendar.service;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

public interface MemberService {
	MemberDTO loginCheck(LoginDTO dto) throws Exception;

	int createMember(MemberDTO dto) throws Exception;

	int idDuplication(String iD);

	int editMember(MemberDTO dto);
}
