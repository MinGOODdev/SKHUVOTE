package com.skhu.vote.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.skhu.vote.service.JwtService;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
	
	private static final String HEADER = "Authorization";
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	JwtService jwtService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		final String token = request.getHeader(HEADER);
		logger.info("token: {}", token);
		
		if(token != null && jwtService.isValid(token)) return true;
		else throw new UnauthorizedException();
	}
	
}
