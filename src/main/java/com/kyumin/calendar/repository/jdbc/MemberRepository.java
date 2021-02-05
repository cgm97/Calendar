package com.kyumin.calendar.repository.jdbc;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

public interface MemberRepository {
	LoginDTO memberCheckById(LoginDTO dto) throws Exception;
	int memberInsert(MemberDTO dto) throws Exception;
	void updateLastLogin(String loginId);
}
