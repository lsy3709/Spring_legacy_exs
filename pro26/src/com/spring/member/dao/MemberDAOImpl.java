package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.member.vo.MemberVO;

//현위치 3-2
//@Repository : 컴포넌트 스캔에서 해당 패키지명을 등록해서, 이 애너테이션을보면.
//컴파일러가 이 클래스는 기능이 Repository(DAO) 역할을 하는구나. 
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	//  @Autowired -> DI, xml 파일에서 해당 빈 객체에 ref 속성으로 주입했음. 
	// 방식은 , 생성자 또는 세터 형식으로 주입했음. 
	// 이제는 	@Autowired 한줄로 대체가능. 
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		
		//실제 디비에 crud 작업 member.xml에서 진행을 합니다. -> 4번
		// sql 문장을 따로 분리해서 관리 하고 있고, 필요한 sql를 아이디 형식으로 불러와 사용중.
	
		//
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	// 만약, 매개변수가 2번째 부분있다면, 이 부분 data를 전달하기위한 인자값.: memberVO
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
}
