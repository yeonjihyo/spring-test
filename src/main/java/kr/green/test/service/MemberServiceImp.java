
package kr.green.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.test.dao.MemberDAO;
import kr.green.test.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService{
	
	@Autowired
	MemberDAO memberDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public MemberVO signin(String id, String pw) {
		MemberVO user = memberDao.getMember(id);
		if(user != null && passwordEncoder.matches(pw, user.getPw())) {
			return user;
		}
		return null;
	}


	@Override
	public void signup(MemberVO mVo) {
		if(mVo ==null) {
			return;
		}
		String encPw =passwordEncoder.encode(mVo.getPw());//입력한패스워드를 가져와서 암호화를 시킴 
		mVo.setPw(encPw);
		memberDao.signup(mVo);
	}


	
}
