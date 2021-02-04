package com.kyumin.calendar.repository;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

public interface MemberRepository {
	LoginDTO memberCheckById(LoginDTO dto) throws Exception;
	int memberInsert(MemberDTO dto) throws Exception;
}
