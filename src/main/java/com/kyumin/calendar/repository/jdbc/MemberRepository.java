package com.kyumin.calendar.repository.jdbc;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

public interface MemberRepository {
	String memberCheckById(LoginDTO dto) throws Exception;
	int memberInsert(MemberDTO dto) throws Exception;
	void updateLastLogin(String loginId);
	int idDupCheck(String iD);
	MemberDTO getMemberById(String id);
}
