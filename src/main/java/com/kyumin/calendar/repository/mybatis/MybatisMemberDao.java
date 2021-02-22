package com.kyumin.calendar.repository.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.repository.MemberRepository;

public class MybatisMemberDao implements MemberRepository {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private SqlSessionFactory factory;
	
	public MybatisMemberDao() {
		this.sqlSession = factory.openSession();
	}

	@Override
	public int memberInsert(MemberDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("memberMapper.memberInsert", dto);
	}

	@Override
	public int memberUpdate(MemberDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLastLogin(String loginId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int idDupCheck(String iD) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String memberCheckById(LoginDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.checkLogin", dto);
	}

	@Override
	public MemberDTO getMemberById(String id) {
		
		return (MemberDTO) sqlSession.selectList("memberMapper.getMemberById", id);
	}

}
