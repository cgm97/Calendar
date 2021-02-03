package com.kyumin.calendar.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BeforeInterceptor extends HandlerInterceptorAdapter{
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        
        if(session.getAttribute("loginedMemberId") == null) {
        	response.sendRedirect(request.getContextPath() + "/login");

        	return false;
        }
        
        return true;
    }
}
