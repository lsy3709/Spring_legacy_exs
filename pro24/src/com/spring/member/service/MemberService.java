package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.spring.member.vo.MemberVO;

public interface MemberService {
	 public List listMembers() throws DataAccessException;
	 public int addMember(MemberVO membeVO) throws DataAccessException;
	 // 현재 위치 2번.
	 // updateMember 기능 추가. 
	 public int updateMember(MemberVO membeVO) throws DataAccessException;
	 
	 // addMember 이용해서, 파라미터에는 해당 회원의 아이디를 받아서.
	 // 리턴의 한명의 회원정보들을 담을 MemberVO 형으로 받도록 합니다. 
	 public MemberVO getMember(String id) throws DataAccessException;
	 public int removeMember(String id) throws DataAccessException;

}
