package com.skhu.vote.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionService {

  /**
   * 세션을 생성합니다.
   *
   * @param key
   * @param value
   * @param <T>
   */
  public <T> void setSession(String key, T value) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    HttpSession session = request.getSession(true);
    session.setAttribute(key, value);
  }

  /**
   * 세션을 가져옵니다.
   *
   * @param key
   * @param <T>
   * @return
   */
  public <T> Object getSession(String key) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    HttpSession session = request.getSession(true);
    return session.getAttribute(key);
  }

  /**
   * 해당 key에 대한 세션이 존재하는지 검사합니다.
   *
   * @param key
   * @return
   */
  public boolean isSession(String key) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    HttpSession session = request.getSession(true);
    if (session.getAttribute(key) == null) return false;
    else return true;
  }

  /**
   * 해당 key에 대한 세션을 제거합니다.
   *
   * @param key
   */
  public void removeSession(String key) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    HttpSession session = request.getSession(true);
    session.removeAttribute(key);
  }

}
