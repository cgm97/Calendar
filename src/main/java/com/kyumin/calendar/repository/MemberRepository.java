package com.kyumin.calendar.repository;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;

public interface MemberRepository {
	int memberInsert(MemberDTO dto);
	int memberUpdate(MemberDTO dto);
	int updateLastLogin(String loginId);
	int deleteById(String id);
	int idDupCheck(String id);
	String memberCheckById(LoginDTO dto);
	MemberDTO getMemberById(String id);
}
