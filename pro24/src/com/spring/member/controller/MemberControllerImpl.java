package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.service.MemberServiceImpl;
import com.spring.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController {
	
	// DI 부분, 다른 클래스의 객체를 주입하는 방법, 가져오기, 포함관계 , has a 관계. 1타 2피., 의존관계
	private MemberService memberService;

	public void setMemberService(MemberServiceImpl memberService) {
		this.memberService = memberService;
	}
	// DI 주입 부분.
	// 인터페이스에서 정의한 3개의 추상메서드를 의무적(강제적) 재정의
	// 시스템에 내가 인터페이스에서 추상 메서드들을 재정의를 했고, 
	// 해당 인터페이스에도 이 메서드가 있는지 컴파일러 너가 확인좀 해봐봐.
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 해당 뷰의 이름을 가져오는 메서드. 아래 메서드 주석 참고.
		String viewName = getViewName(request);
		
		// 회원 정보 전체 데이터가 필요해 -> memberService 에게 요청함. 
		// 1번 -> 2번 -> 3번 ->4번 과정을 하고, 다시 역순으로 요청한 데이터가 가져오기.
		// 역순의 작업으로 받은 회원의 목록을 받는 부분이 :membersList
		List membersList = memberService.listMembers();
		
		// ModelAndView 통해서 해당 뷰와 데이터를 전달하는 과정.
		//viewName : 해당 요청한 매핑 주소의 메서드와 동일.
		ModelAndView mav = new ModelAndView(viewName);
		// ModelAndView , 데이터를 전달하는 과정(여기서 등록하면),
		// 해당 뷰에서 가져가서 사용 가능. ( getter 형식.)
		mav.addObject("membersList", membersList);
		return mav;
	}

	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();
		/*
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email = request.getParameter("email");
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		 */
		// 위에 사용자로 부터 입력 받은 값을 다시 임시 객체 memberVO에 할당 하는 작업
		// bind 로 대체 가능함. 
		bind(request, memberVO);
		
		// memberVO 에 값이 setter로 들어간 효과가 있음. 
		
		int result = 0;
		result = memberService.addMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@Override
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	///member/updateForm.do
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 한명의 회원의 정보가 필요하고, 이것 해당 뷰에 던져줘야, 
		// 회원 수정창에서 회원의 정보를 불러올수 있음. 
		// 뷰에서 ?id=1234 의 내용을 
		// 여기서 가져와서 사용하는 형식. 
		String id=request.getParameter("id");
	
		//컨트롤러 1번 위치에서 -> 서비스로 요청(2번위치)
		// 예) id=1234 인 정보를 조회해서 가져와줘.
		// 한명의 회원의 정보이니까, 받을 때에도 , 한명의 타입으로 받으면 됩니다. 
		// 목록 출력시 예제 코드 이용. 
		//	List membersList = memberService.listMembers();
		// 이용하는 방법은 memberService 객체에 들어 있는 메서드를 이용함. 
		MemberVO memberVO = memberService.getMember(id);
		
		//이작업 해당 뷰로만 전달하는 작업
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		// 한명 회원 정보를 담은 객체를 뷰에 넘기기 작업. 
		mav.addObject("memberVO", memberVO);
		return mav;
	}
	
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	
// 21장 com.spring.ex02.UserController 여기파일에
	// 주석을 다 달았음. 
	// 필요하면, 내용이 같으니 복사해서 사용해도 됩니다. 
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

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}
	
	// addMember 의 기능을 복사해서 업데이트 기능으로 변경해서 사용 할 예정. 
	@Override
	public ModelAndView updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();
		
		// 회원 아이디는 읽기전용이라서 그대로 가져오고,
		// 나머지 정보는 변경된 내용을 가져오는 작업. 
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email = request.getParameter("email");
		
		// 변경된 데이터를 옮기기 위해서, 임시 객체에 담는 작업. 
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		
		// 실제 디비 작업 합니다. 
		// 1번 -> 2번 -> 3번 -> 4번 -> 5번
		// 한명 회원의 정보를 담은 객체를 전달하는 과정.
		// result 수정이 되었다면 1로 반환해서 확인하는 용도.
// 현재 위치 1번
		int result = 0;
		// 업데이트 하는 기능은 memberService 2번에게 요청하기. 
		result = memberService.updateMember(memberVO);
		if(result == 1) {
			String str = "회원 수정 성공 했음." ;
		} else {
			String str2 = "회원 수정 성공 안했음.";
		}
//		바인딩 작업은 뒤에서 설명 후 사용할 예정.
//		bind(request, memberVO);
		// 수정이 되었다면, 목록으로 가기. 
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}	

	
	
	
	
	
	
}
