package com.kyumin.calendar.common;

import javax.servlet.RequestDispatcher;
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
        logger.info("현재 접속 중인 ID 확인 : "+session.getAttribute("loginedMember"));
        
        if(session.getAttribute("loginedMember") == null) {
//        	session.setAttribute("infomation", "로그인 후 사용 가능합니다.");
//        	response.sendRedirect(request.getContextPath() + "/login");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
        	request.setAttribute("infomation", "로그인 후 사용 가능합니다.");
        	dispatcher.forward(request, response);

        	return false;
        }
        return true;
    }
}
