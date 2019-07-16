package kr.green.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");
		if(user == null) {//비회원일 경우 
			response.sendRedirect(request.getContextPath() +"/");
		}
		return true;
	}
	//세션에 user 정보를 가져와서 null값이면 로그인 화면으로 보냄
	//정보가 있으면 그냥 넘어감
	
}