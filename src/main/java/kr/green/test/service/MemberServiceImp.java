
package kr.green.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.MemberDAO;
import kr.green.test.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService{
	
	@Autowired
	MemberDAO memberDao;
	
	@Override
	public MemberVO signin(String id, String pw) {
		MemberVO user = memberDao.getMember(id);
		if(user != null && user.getPw().equals(pw)) {
			return user;
		}
		return null;
	}
	
	//@Override
	//public boolean signin(MemberVo mVo) {
	//	if(memberDao.getMember(mVo.getId()).equals(mVo)) {
	//		return true;
	//	}
	//	return false;
	//}
	
}
