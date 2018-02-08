package com.skhu.vote.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class SessionService {

	// 세션 생성
	public <T> void setSession(String key, T value) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute(key, value);
	}

	// 세션 획득
	public <T> Object getSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(true);
		return session.getAttribute(key);
	}

	// 세션 체크
	public boolean isSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(true);
		if(session.getAttribute(key) == null) return false;
		else return true;
	}

	// 세션 제거
	public void removeSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(true);
		session.removeAttribute(key);
	}
}
