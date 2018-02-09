package com.skhu.vote.config;

/*
	1) ExpiredJwtException : JWT를 생성할 때 지정한 유효기간 초과할 때.
	
	2) UnsupportedJwtException : 예상하는 형식과 일치하지 않는 특정 형식이나 구성의 JWT일 때
	
	3) MalformedJwtException : JWT가 올바르게 구성되지 않았을 때
	
	4) SignatureException :  JWT의 기존 서명을 확인하지 못했을 때
	
	5) IllegalArgumentException
*/

public class UnauthorizedException extends RuntimeException {
	
	private static final long serialVersionUID = -2238030302650813813L;
	
	public UnauthorizedException() {
		super("계정 권한이 유효하지 않습니다.");
	}
}
