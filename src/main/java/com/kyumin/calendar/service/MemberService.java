package com.kyumin.calendar.service;

import com.kyumin.calendar.domain.LoginDTO;

public interface MemberService {
	LoginDTO loginCheck(LoginDTO dto) throws Exception;
}
