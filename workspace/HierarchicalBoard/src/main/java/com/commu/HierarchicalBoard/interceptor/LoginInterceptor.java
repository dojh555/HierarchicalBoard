package com.commu.HierarchicalBoard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		HttpSession session = request.getSession();
		System.out.println("LoginInterceptor");

		if(isAjaxRequest(request)){//ajax 요청시 redirect는 되지 않으니 error 함수에 메시지 보내기
			//response.setStatus(999);
			//response.sendError(400, "Length too long");
			
		}else {//일반 요청시 session에 로그인후 인지 확인
			if(session.getAttribute("memNum")==null) {//memNum 이 없다면 로그인폼으로 요청
				response.sendRedirect("/member/loginForm");
				return false;
			}
		}
		
		return true;
	}
	
    private boolean isAjaxRequest(HttpServletRequest req) {
        String ajaxHeader = "AJAX";
        return req.getHeader(ajaxHeader) != null && req.getHeader(ajaxHeader).equals(Boolean.TRUE.toString());
    }

}
