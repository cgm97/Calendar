package com.kyumin.calendar.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.kyumin.calendar.common.JdbcUtil;
import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.repository.jdbc.MemberRepository;

@Repository
public class JdbcMemberDao implements MemberRepository {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Override // 로그인 인증
	public LoginDTO memberCheckById(LoginDTO dto){
	
		String sql = "SELECT NAME FROM MEMBER WHERE LOGINID=? AND LOGINPW=?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getLoginId());
			pstmt.setString(2, dto.getLoginPw());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setName(rs.getString("NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(rs, pstmt, conn);

		return dto;
	}

	@Override
	public int memberInsert(MemberDTO dto){
		int result = -1;
		
		String sql = "INSERT INTO MEMBER (MEMBERNO, LOGINID, LOGINPW ,NAME ,EMAIL ,REGDATE, LASTLOGIN)"+
						"VALUES(MEMBERNO.NEXTVAL, ?, ?, ?, ?,SYSDATE,SYSDATE)";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getLoginId());
			pstmt.setString(2, dto.getLoginPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {

		}
		
		JdbcUtil.close(pstmt, conn);
		
		return result;
	}
	
	@Override
	public void updateLastLogin(String loginId) {
		String sql = "UPDATE MEMBER SET LASTLOGIN=SYSDATE WHERE LOGINID=?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(pstmt, conn);
	}
}
