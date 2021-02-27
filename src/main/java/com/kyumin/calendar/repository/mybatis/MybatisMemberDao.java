package com.kyumin.calendar.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.kyumin.calendar.domain.LoginDTO;
import com.kyumin.calendar.domain.MemberDTO;
import com.kyumin.calendar.repository.MemberRepository;

@Repository
@Primary
public class MybatisMemberDao implements MemberRepository {
	
	@Autowired
	private SqlSession sqlSession;
	private static final String NAMESPACE = "memberMapper.";
	
	@Override
	public int memberInsert(MemberDTO dto) {
		return sqlSession.insert(NAMESPACE+"memberInsert", dto);
	}

	@Override
	public int memberUpdate(MemberDTO dto) {
		return sqlSession.update(NAMESPACE+"memberUpdate", dto);
	}

	@Override
	public int updateLastLogin(String loginId) {
		return sqlSession.update(NAMESPACE+"updateLastLogin", loginId);
	}

	@Override
	public int deleteById(String id) {
		return sqlSession.delete(NAMESPACE+"deleteById", id);
	}

	@Override
	public int duplicateId(String iD) {
		return sqlSession.selectOne(NAMESPACE+"duplicateId", iD);
	}

	@Override
	public String memberCheckById(LoginDTO dto) {
		return sqlSession.selectOne(NAMESPACE+"checkLogin", dto);
	}

	@Override
	public MemberDTO getMemberById(String id) {		
		return (MemberDTO) sqlSession.selectOne(NAMESPACE+"getMemberById", id);
	}

}
