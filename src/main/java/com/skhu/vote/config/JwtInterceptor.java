package com.skhu.vote.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.SessionService;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

	private static final String HEADER = "Authorization";
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	JwtService jwtService;
	@Autowired
	SessionService sessionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader(HEADER);

		if(token == null) return false;
		else if(!jwtService.isValid(token)) return false;
		else {
			logger.info("token: {}", token);
			return true;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

	@Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    }
}
