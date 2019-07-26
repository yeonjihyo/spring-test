
package kr.green.test.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


	@Override
	public boolean isId(String id) {
		if(memberDao.getMember(id) == null){
			return false;
		}
		return true;
	}


	@Override
	public MemberVO modify(MemberVO user, String oldPw) {
		if(user == null) {
			return null;
		}
		MemberVO oUser= memberDao.getMember(user.getId());
		if(!passwordEncoder.matches(oldPw, oUser.getPw())) {
			return null;
		}
		if(user.getPw().length()==0) { 
			//새 비밀번호가 입력되지 않은 경우 기존비밀번호 정보를 가져와 새 비밀번호로 설정한다.
			user.setPw(oUser.getPw());
			System.out.println("새비번이없는경우 ");
		}else {
			//새 비밀번호가 입력된 경우 디비에 저장하기 전에 암호화를 해야한다
			String encPw=passwordEncoder.encode(user.getPw());
			user.setPw(encPw);
			System.out.println("새비번이있는경우 ");
		}
		memberDao.modify(user);
		return user;
		
	}


	@Override
	public boolean updateUserToSession(HttpServletRequest r, MemberVO nUser) {
		if(nUser ==null) {
			return false;
		}
		HttpSession s=r.getSession();
		s.removeAttribute("user");//이전 회원정보 제거
		s.setAttribute("user", nUser);//새회원정보 추가 
		return true;
	}


	


	
}
