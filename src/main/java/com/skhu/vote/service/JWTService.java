package com.skhu.vote.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class JWTService {

	private String createToken(String id, String name, int type, Date lastLogin) {
		SignatureAlgorithm signature = SignatureAlgorithm.HS
	}

}
