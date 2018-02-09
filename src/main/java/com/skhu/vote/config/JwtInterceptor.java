package com.skhu.vote.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.skhu.vote.service.JwtService;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
	
	private static final String HEADER_AUTH = "Auth";
	
	@Autowired
	JwtService jwtService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		final String token = request.getHeader(HEADER_AUTH);
		
		if(token != null && jwtService.isValid(token)) return true;
		else throw new UnauthorizedException();
	}
	
}
