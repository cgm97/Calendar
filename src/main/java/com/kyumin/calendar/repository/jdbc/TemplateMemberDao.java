package com.kyumin.calendar.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.repository.MemberRepository;
import com.kyumin.calendar.repository.jdbc.mapper.MemberRowMapper;

@Repository
//@Primary
public class TemplateMemberDao implements MemberRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String memberCheckById(LoginDTO dto){
		String sql = "SELECT LOGINID FROM MEMBER WHERE LOGINID=? AND LOGINPW=?";
		String id = null;
		try {
			id = jdbcTemplate.queryForObject(sql, new Object[]{dto.getLoginId(),dto.getLoginPw()}, String.class);			
			return id;
		}catch(Exception e) {
			return id;
		}
	}

	@Override
	public int memberInsert(MemberDTO dto){
		int result = -1;

		String sql = "INSERT INTO MEMBER (MEMBERNO, LOGINID, LOGINPW ,NAME ,EMAIL ,REGDATE, LASTLOGIN)"+
				"VALUES(MEMBERNO.NEXTVAL, ?, ?, ?, ?,SYSDATE,SYSDATE)";
		try {
			result = jdbcTemplate.update(sql, dto.getLoginId(), dto.getLoginPw(), dto.getName(), dto.getEmail());
			return result;
		} catch (Exception e) {
			return result;
		}		
	}

	@Override
	public int updateLastLogin(String loginId){
		String sql = "UPDATE MEMBER SET LASTLOGIN=SYSDATE WHERE LOGINID=?";
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, loginId);
			return result;
		}catch(EmptyResultDataAccessException e) {
			return result;
		}
	}

	@Override
	public int idDupCheck(String id) {
		String sql = "SELECT COUNT(LOGINID) FROM MEMBER WHERE LOGINID=?";
		
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public MemberDTO getMemberById(String id) {
		String sql = "select * from MEMBER WHERE LOGINID=?";
		
		return jdbcTemplate.queryForObject(sql, new MemberRowMapper(), id);
	}

	@Override
	public int memberUpdate(MemberDTO dto) {
		String sql = "UPDATE MEMBER SET LOGINPW=?, NAME=?, EMAIL=? WHERE LOGINID=?";
		
		return jdbcTemplate.update(sql,dto.getLoginPw(),dto.getName(),dto.getEmail(),dto.getLoginId());
	}

	@Override
	public int deleteById(String id) {
		String sql = "DELETE FROM MEMBER WHERE LOGINID=?";
		
		return jdbcTemplate.update(sql, id);
	}

}
