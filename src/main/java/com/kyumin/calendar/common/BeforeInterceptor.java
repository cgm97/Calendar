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

	// 페이지 요청 정보
//	private void savePage(HttpServletRequest request, HttpSession session) {
//		String uri = request.getRequestURI();
//		String query = request.getQueryString();
//		if (query == null || query.equals("null")){
//			query="";
//		}else {
//			query = "?" + query;
//		}	
//		logger.info("저장된 이전 페이지 : " + (uri + query));
//		session.setAttribute("savePage", uri + query);
//	}
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        logger.info("현재 접속 중인 ID 확인 : "+session.getAttribute("loginedMember"));
        
        if (session.getAttribute("loginedMember") == null) {
			/*
			 * session.setAttribute("infomation", "로그인 후 사용 가능합니다.");
			 * response.sendRedirect(request.getContextPath() + "/login"); savePage(request,
			 * session);
			 */
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
        	request.setAttribute("infomation", "로그인 후 사용 가능합니다.");
        	dispatcher.forward(request, response);

        	return false;
        }
        return true;
    }
	
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		HttpSession session = request.getSession();
//		logger.info("포스트핸들 : " + session.getAttribute("savePage"));
//		if(session.getAttribute("loginedMember") != null) {
//			logger.info("새로운 로그인 완료");
//			Object savePage = session.getAttribute("savePage");
//			response.sendRedirect(savePage != null ? (String) session.getAttribute("savePage") : "/");
//		}
//	}
}
