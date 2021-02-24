package com.kyumin.calendar.repository.jdbc;

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
import com.kyumin.calendar.repository.MemberRepository;

@Repository
//@Primary
public class JdbcMemberDao implements MemberRepository {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Override // 로그인 인증
	public String memberCheckById(LoginDTO dto){
		String id = null;
		String sql = "SELECT LOGINID FROM MEMBER WHERE LOGINID=? AND LOGINPW=?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, dto.getLoginId());
			pstmt.setString(2, dto.getLoginPw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("LOGINID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(rs, pstmt, conn);

		return id;
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
			e.printStackTrace();
		}
		
		JdbcUtil.close(pstmt, conn);
		
		return result;
	}
	
	@Override
	public int updateLastLogin(String loginId) {
		String sql = "UPDATE MEMBER SET LASTLOGIN=SYSDATE WHERE LOGINID=?";
		int result = -1;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(pstmt, conn);
		
		return result;
	}

	@Override
	public int idDupCheck(String id) {
		int result = 0; // 존재 x
		String sql = "SELECT LOGINID FROM MEMBER WHERE LOGINID=?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, id);			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1; //존재 o
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(rs, pstmt, conn);
		return result;
	}

	@Override
	public MemberDTO getMemberById(String id) {
		String sql = "select * from MEMBER WHERE LOGINID=?";
		MemberDTO dto = new MemberDTO();
		
		try {
			conn = dataSource.getConnection();			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setLoginId(rs.getString("LOGINID"));
				dto.setLoginPw(rs.getString("LOGINPW"));
				dto.setName(rs.getString("NAME"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setRegDate(rs.getString("REGDATE"));
				dto.setLastLogin(rs.getString("LASTLOGIN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		JdbcUtil.close(rs, pstmt, conn);
		
		return dto;
	}

	@Override
	public int memberUpdate(MemberDTO dto) {
		int result = -1;
		String sql = "UPDATE MEMBER SET LOGINPW=?, NAME=?, EMAIL=? WHERE LOGINID=?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getLoginPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getLoginId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		JdbcUtil.close(pstmt, conn);
		
		return result;
	}

	@Override
	public int deleteById(String id) {
		int result = -1;
		String sql = "DELETE FROM MEMBER WHERE LOGINID=?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		JdbcUtil.close(pstmt, conn);
		
		return result;
	}
}
