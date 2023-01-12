package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

// 3번위치.
public class MemberDAOImpl implements MemberDAO {
	
	// DI 주입.
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List selectAllMemberList() throws DataAccessException {
		
		List<MemberVO> membersList = null;
		
		// 현재위치 3번 MemberDAOImpl -> SqlSession(DataSource : 4번위치)
		// 23장의 Mybatis : CRUD 
		// mybatis -> mappers -> member.xml 이 파일.
		// 호출하는 부분이 아이디를 호출하는데 : selectAllMemberList
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result =  sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	
	// 기능 구현을 목록 가져오기 부분을 참고.
	// 한명의 회원의 정보를 받은 타입을 정하고. :  MemberVO memberVO
	// 실제 작업은 sqlSession.selectOne(id1, id2)
	// id1 -> 4번 위치에 있는 마이바티스에 등록된 sql 태그
	// id1 -> 이용하는 형식. 예) "mapper.member.deleteMember"
	
	// id2 -> 넘어온 값 id =1234  의미. 
	@Override
	public MemberVO getMember(String id) throws DataAccessException {
		MemberVO memberVO = (MemberVO) sqlSession.selectOne("mapper.member.selectMemberById", id);
		return memberVO;
	}

	// 넘어온 한명의 회원의 정보를 가지고, update 하기. 
	// member.xml에서 업데이트하는 태그가 있다면 그대로 사용하고, 
	// 만약, 없다면 태그를 만들어야함. 
	// 찾아보니, 아이디가 : updateMember 만들어져 있어서, 사용함.
	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.update("mapper.member.updateMember", memberVO);
		return result;
	}
}
