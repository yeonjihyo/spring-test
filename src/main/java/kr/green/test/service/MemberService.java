package kr.green.test.service;


import kr.green.test.vo.MemberVO;

public interface MemberService {

	MemberVO signin(String id, String pw);

//	boolean signin(MemberVO mVo);

}
