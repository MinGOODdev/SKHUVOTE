package com.skhu.vote.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class SessionService {
	
	// create Session (세션 생성)
	public <T> void setSession(String key, T value) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute(key, value);
	}
	
	// get Session (세션 가져오기)
	public <T> Object getSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(true);
		return session.getAttribute(key);
	}
	
	// isValid Session (세션 있는지 검사)
	public boolean isSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(true);
		if(session.getAttribute(key) == null) return false;
		else return true;
	}
	
	// remove Session (세션 제거)
	public void removeSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(true);
		session.removeAttribute(key);
		
	}
	
}
