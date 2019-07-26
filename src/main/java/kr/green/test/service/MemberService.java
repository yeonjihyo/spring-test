package kr.green.test.service;


import javax.servlet.http.HttpServletRequest;

import kr.green.test.vo.MemberVO;

public interface MemberService {

	MemberVO signin(String id, String pw);

	void signup(MemberVO mVo);

	boolean isId(String id);

	
	MemberVO modify(MemberVO user, String oldPw);

	boolean updateUserToSession(HttpServletRequest r, MemberVO nUser);

	




}
