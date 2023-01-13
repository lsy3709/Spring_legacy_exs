package com.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

//현위치 2-2
// @Service 
//@Service  : 컴포넌트 스캔에서 해당 패키지명을 등록해서, 이 애너테이션을보면.
//컴파일러가 이 클래스는 기능이 Service 역할을 하는구나. 
@Service("memberService")

//단위기능 1, 단위기능 2, 하나의 논리적인 구조 묶어서
// all, nothing 
// 2개의 단위기능이 다 정상 동작 해야지 마지막에 커밋함. 
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}

	@Override
	public int addMember(MemberVO member) throws DataAccessException {
		return memberDAO.insertMember(member);
	}

	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.deleteMember(id);
	}
}
