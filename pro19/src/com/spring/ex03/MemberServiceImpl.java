package com.spring.ex03;

public class MemberServiceImpl implements MemberService {
	// member.xml 에 해당 아이디 이름 :memberService
	// 등록이 된 상태.
	// memberDAO : 라는 객체도 같이 들어가 있다. 주입이 되었다. 
	// 포함 하고 있다. 의존관계다. 
	
	// 선언만 하고, 할당을 안했음.: 즉 현재 상태는 null인 상태
	private MemberDAO memberDAO;
	
	//여기에 값을 주입 하는 방법은 2가지 
	// set , constructor 할거냐, 
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void listMembers() {
		memberDAO.listMembers();
	}
}
