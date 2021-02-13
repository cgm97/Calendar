package com.kyumin.calendar.repository;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

public interface MemberRepository {
	String memberCheckById(LoginDTO dto) throws Exception;
	int memberInsert(MemberDTO dto) throws Exception;
	int updateLastLogin(String loginId) throws Exception;
	int idDupCheck(String iD);
	MemberDTO getMemberById(String id);
	int memberUpdate(MemberDTO dto);
}
