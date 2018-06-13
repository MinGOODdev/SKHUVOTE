package com.skhu.vote.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 요청이 들어오면 Filter -> Interceptor -> Controller 순서로 요청이 들어가게 되는데
 * Interceptor에서 Token을 검증하고, Controller로 넘겨줄지 접근을 차단할지 설정을 할 수 있다.
 */

/*
 *  Access-Control-Allow-Origin :
 *  파라미터는 리소스에 접근하는 URI를 특정한다. 브라우저는 이것을 반드시 강제해야 한다.
 *  자격 증명 없는 요청에 대해 서버는 와일드 카드는 "*"를 특정할 수 있다.
 *  ex) Access-Control-Allow-Origin: http://mozilla.com
 *
 *  Access-Control-Expose-Headers :
 *  브라우저가 접근할 수 있도록 해주는 서버 화이트리스트 헤더를 허용한다.
 *
 *  Access-Control-Max-Age :
 *  사전 전달의 결과가 얼마나 오랫동안 캐시될 수 있는지를 말한다.
 */

@Component
public class CorsFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    // TODO appKey가 유효한지 확인 후 열어줄 것 (After Confirm appKey Valid -> Open)
    HttpServletResponse response = (HttpServletResponse) res;

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, X-Requested-With, Origin, Content-Type, accept, authorization, X-Auth-Token, Content-Disposition");
    response.setHeader("Access-Control-Allow-Credentials", "false");
    chain.doFilter(req, res);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void destroy() {

  }

}