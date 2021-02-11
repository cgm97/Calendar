package com.kyumin.calendar.repository.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.kyumin.calendar.domain.MemberDTO;

public class MemberRowMapper implements RowMapper<MemberDTO> {

	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO dto = new MemberDTO();
		
		dto.setLoginId(rs.getString("LOGINID"));
		dto.setLoginPw(rs.getString("LOGINPW"));
		dto.setName(rs.getString("NAME"));
		dto.setEmail(rs.getString("EMAIL"));
		dto.setRegDate(rs.getDate("REGDATE"));
		dto.setLastDate(rs.getDate("LASTLOGIN"));
		
		return dto;
	}

}
