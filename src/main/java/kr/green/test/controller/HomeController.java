package kr.green.test.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.green.test.dao.MemberDAO;
import kr.green.test.service.MemberService;
import kr.green.test.vo.MemberVO;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeGet() {
		logger.info("메인 페이지");
		
		return "home";
	}
	//로그인
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(Model model, String id, String pw) {
		logger.info("로그인 진행 중");
		MemberVO user = memberService.signin(id,pw); 
		if( user != null) {
			logger.info("로그인 성공");
			logger.info(user.toString());
			model.addAttribute("user",user);//모델에 유저 정보를 담아서 보냄 
		}else {
			logger.info("로그인 실패");
		}
			
		return "redirect:/board/list";
	}
	//로그아웃
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signoutGet(HttpServletRequest r) {
		logger.info("로그아웃");
		//세션에저장한 user정보를 제거 
		r.getSession().removeAttribute("user");
		return "redirect:/";
	}
	
	//회원가입
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupGet() {
		logger.info("회원가입 페이지");
		
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(MemberVO mVo) {
		logger.info("회원가입 진행중");
		
		memberService.signup(mVo);
		return "redirect:/";
	}
	
	//아이디 중복체크
	@RequestMapping(value ="/dup")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String id){

	   Map<Object, Object> map = new HashMap<Object, Object>();   
	   
	   boolean isId = memberService.isId(id);
	   map.put("isId", isId);
	   System.out.println(id);
	   return map;
	}
	
	//비밀번호찾기
	@RequestMapping(value = "/password/find")
	public String passwordFind() {
		logger.info("비밀번호 찾기 페이지");
		
		return "find";
	}
}
