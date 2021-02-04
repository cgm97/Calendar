package com.kyumin.calendar.service;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

public interface MemberService {
	LoginDTO loginCheck(LoginDTO dto) throws Exception;

	int createMember(MemberDTO dto) throws Exception;
}
