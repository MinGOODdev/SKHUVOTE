package com.skhu.vote.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.skhu.vote.config.UnauthorizedException;
import com.skhu.vote.utils.SHA512EncryptUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
		
	private static final String SALT = "SeCReT";
	
	public <T> String createToken(String key, T data) {
		String jwt = Jwts.builder()
					.setHeaderParam("typ", "JWT")
					.setHeaderParam("regDate", System.currentTimeMillis())
					.claim(key, data)
					.signWith(SignatureAlgorithm.HS512, SHA512EncryptUtils.encrypt(SALT))
					.compact();
		return jwt;
	}
	
	public boolean isValid(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser()
									.setSigningKey(SHA512EncryptUtils.encrypt(SALT))
									.parseClaimsJws(jwt);
			return true;
		// claims으로 변환 도중 예외가 발생하면 유효하지 않은 토큰으로 판단
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
	}
	
	// JWT에 넣어놓은 데이터 가져오기 (굳이 이게 필요하려나 일단 ㄱㄱ)
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("Auth");
		
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
						.setSigningKey(SHA512EncryptUtils.encrypt(SALT))
						.parseClaimsJws(jwt);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
		return value;
	}
	
}
