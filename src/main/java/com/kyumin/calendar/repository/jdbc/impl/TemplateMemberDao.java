package com.kyumin.calendar.repository.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.repository.jdbc.MemberRepository;

@Repository
//@Primary
public class TemplateMemberDao implements MemberRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String memberCheckById(LoginDTO dto){
		String sql = "SELECT LOGINID FROM MEMBER WHERE LOGINID=? AND LOGINPW=?";
		try {
			String id = jdbcTemplate.queryForObject(sql, new Object[]{dto.getLoginId(),dto.getLoginPw()}, String.class);	
			return id;
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int memberInsert(MemberDTO dto){
		String sql = "INSERT INTO MEMBER (MEMBERNO, LOGINID, LOGINPW ,NAME ,EMAIL ,REGDATE, LASTLOGIN)"+
				"VALUES(MEMBERNO.NEXTVAL, ?, ?, ?, ?,SYSDATE,SYSDATE)";

		int result = jdbcTemplate.update(sql, dto.getLoginId(), dto.getLoginPw(), dto.getName(), dto.getEmail());
		return result;
	}

	@Override
	public void updateLastLogin(String loginId) {
		String sql = "UPDATE MEMBER SET LASTLOGIN=SYSDATE WHERE LOGINID=?";
		jdbcTemplate.update(sql, loginId);
	}

	@Override
	public int idDupCheck(String iD) {
		String sql = "SELECT LOGINID FROM MEMBER WHERE LOGINID=?";
		
		return jdbcTemplate.update(sql, iD);
	}

	@Override
	public MemberDTO getMemberById(String id) {
		String sql = "select * from MEMBER WHERE LOGINID=?";
		
		return jdbcTemplate.queryForObject(sql, new MemberRowMapper(), id);
	}

}
