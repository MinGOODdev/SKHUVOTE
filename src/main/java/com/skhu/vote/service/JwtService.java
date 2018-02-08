package com.skhu.vote.service;

import org.springframework.stereotype.Service;

import com.skhu.vote.utils.SHA512EncryptUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	private static final String SALT = "Secret";
	
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
		} catch (Exception e) {
			return false;
		}
	}
	
}
