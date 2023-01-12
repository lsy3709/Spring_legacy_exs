package com.spring.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.spring.member.vo.MemberVO;


public interface MemberDAO {
	//3번 위치
	 public List selectAllMemberList() throws DataAccessException;
	 public int insertMember(MemberVO memberVO) throws DataAccessException ;
	 
	 //getMember 라는 메서드 만들기. 
	 public MemberVO getMember(String id) throws DataAccessException ;
	 public int deleteMember(String id) throws DataAccessException;
	 

}
