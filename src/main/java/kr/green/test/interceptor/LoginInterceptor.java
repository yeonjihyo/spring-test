package kr.green.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.green.test.vo.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(
	    HttpServletRequest request, 
	    HttpServletResponse response, 
	    Object handler, 
	    ModelAndView modelAndView)
	    throws Exception {
	    ModelMap modelMap = modelAndView.getModelMap();//컨트롤러에서 jsp로 넘어갈때 모델에추가한속성user를 가로채서저장 
	    MemberVO user = (MemberVO)modelMap.get("user");

	    if(user != null) {//로그인에 성공했다면
	        HttpSession s = request.getSession();
	        s.setAttribute("user", user);//세션에 저장
	    }
	}
}
