package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;



//현위치 1-1
// @Controller : 컴포넌트 스캔에서 해당 패키지명을 등록해서, 이 애너테이션을보면.
// 컴파일러가 이 클래스는 기능이 Controller 역할을 하는구나. 
// 
@Controller("memberController")
public class MemberControllerImpl   implements MemberController {
	
	//  @Autowired -> DI, xml 파일에서 해당 빈 객체에 ref 속성으로 주입했음. 
	// 방식은 , 생성자 또는 세터 형식으로 주입했음. 
	// 이제는 	@Autowired 한줄로 대체가능. 
	@Autowired
	private MemberService memberService;
	@Autowired
	MemberVO memberVO ;
	
	
	//현위치 1-1
	// @RequestMapping : value="/member/listMembers.do" 속성으로 오는 주소부분에 대해서 
	// 처리하는 역할. 지금은 메서드 부분에 표기 되어 있지만, 
	// 클래스에 설정해서, 하위에 나눠서 작업도 가능함. 
	@Override
	@RequestMapping(value="/member/listMembers.do" ,method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	
	//현위치 1-1
	// @ModelAttribute("member") MemberVO member
	// @ModelAttribute : MemberVO 의 형으로 데이터를 받겠다. 
		// member : 뷰에서 사용할 변수명.
	@Override
	@RequestMapping(value="/member/addMember.do" ,method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	//현위치 1-1
	//// @RequestParam -> request.getParameter("id"); 대체용.
	@Override
	@RequestMapping(value="/member/removeMember.do" ,method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	/*@RequestMapping(value = { "/member/loginForm.do", "/member/memberForm.do" }, method =  RequestMethod.GET)*/
	@RequestMapping(value = "/member/*Form.do", method =  RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/"), viewName.length());
		}
		return viewName;
	}


}
