package com.kyumin.calendar.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BeforeInterceptor extends HandlerInterceptorAdapter{
	protected Logger logger = LoggerFactory.getLogger(BeforeInterceptor.class);

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        logger.info("현재 접속 중인 ID 확인 : "+session.getAttribute("loginedMemberId"));
        
        if(session.getAttribute("loginedMemberId") == null) {
        	response.sendRedirect(request.getContextPath() + "/login");

        	return false;
        }
        return true;
    }
}
