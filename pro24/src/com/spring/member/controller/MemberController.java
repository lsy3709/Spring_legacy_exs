package com.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;

public interface MemberController {
	//추상 메서드
	// 역할. 작업 량이 많고, 작업 파일에 메서드들만 다 섞여있으면
	// 나중에 유지 보수 하기 힘듦. 
	// 작업 한 메서드들을 파악하기 쉽고, 확장 부분 쉽다.등. 단, 복잡도 증가. 
	// 강제성도 있음. 해당 기능은 웹 개발시 의무적으로 포함해야한다는 의미.
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//회원 수정 창에서 입력된 내용을 처리하는 업데이트 기능을 추가합니다. 참고는 회원가입 기능을 참고.
	public ModelAndView updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
