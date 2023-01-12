package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

/*@Transactional(propagation=Propagation.REQUIRED) */
public class MemberServiceImpl  implements MemberService{
	
	// DI 주입. 포함 관계 다른 객체 가져온다. 
	   private MemberDAO memberDAO;
	   public void setMemberDAO(MemberDAO memberDAO){
	      this.memberDAO = memberDAO;
	   }

	   @Override
	   public List listMembers() throws DataAccessException {
		   //
	      List membersList = null;
	      //MemberServiceImpl (2번 서비스) -> memberDAO(3번) 요청.
	      membersList = memberDAO.selectAllMemberList();
	      return membersList;
	   }

	   @Override
	   public int addMember(MemberVO memberVO) throws DataAccessException {
	     return memberDAO.insertMember(memberVO);
	   }


	   @Override
	   public int removeMember(String id) throws DataAccessException {
	      return memberDAO.deleteMember(id);
	   }

	   //회원 가입 하는 부분에서 샘플 코드 이용.
	   // return memberDAO.insertMember(memberVO);
	   // 현재 위치는 2번. 디비에 직접 접근하는 기능이 있는 3번에게 도움을 요청. 
	@Override
	public MemberVO getMember(String id) throws DataAccessException {

		return memberDAO.getMember(id);
	}


}
