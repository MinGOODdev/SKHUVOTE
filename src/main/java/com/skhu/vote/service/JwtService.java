package com.skhu.vote.service;

import com.skhu.vote.utils.SHA256Utils;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
@Service
public class JwtService {

  private static final String SALT = "SeCReT";

  /**
   * JWT를 생성합니다.
   *
   * @param key
   * @param data
   * @param <T>
   * @return
   */
  public <T> String createToken(String key, T data) {
    String jwt = Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("regDate", System.currentTimeMillis())
            .setId(key)
            .claim(key, data)
            .signWith(SignatureAlgorithm.HS256, SHA256Utils.encrypt(SALT))
            .setExpiration(getTime())
            .compact();
    return jwt;
  }

  /**
   * JWT가 유효한지 검사합니다.
   *
   * @param jwt
   * @return
   */
  public boolean isValid(String jwt) {
    try {
      Jws<Claims> claims = Jwts.parser()
              .setSigningKey(SHA256Utils.encrypt(SALT))
              .parseClaimsJws(jwt);
      return true;
      // claims으로 변환 도중 예외가 발생하면 유효하지 않은 토큰으로 판단
    } catch (ClaimJwtException e) {
      e.printStackTrace();
      return false;
    } catch (MalformedJwtException e) {
      e.printStackTrace();
      return false;
    } catch (SignatureException e) {
      e.printStackTrace();
      return false;
    }
  }

  // JWT에 넣어놓은 데이터 가져오기
  public Map<String, Object> getTokenData(String key) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    String jwt = request.getHeader("Authorization");

    Jws<Claims> claims = null;
    try {
      claims = Jwts.parser()
              .setSigningKey(SHA256Utils.encrypt(SALT))
              .parseClaimsJws(jwt);
    } catch (ClaimJwtException e) {
      e.printStackTrace();
    } catch (MalformedJwtException e) {
      e.printStackTrace();
    } catch (SignatureException e) {
      e.printStackTrace();
    }
    Map<String, Object> value = (LinkedHashMap<String, Object>) claims.getBody().get(key);
    return value;
  }

  public String getAuthId(String key) {
    return this.getTokenData(key).get("id").toString();
  }


  public Date getTime() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.HOUR, 1);
    return cal.getTime();
  }

}
