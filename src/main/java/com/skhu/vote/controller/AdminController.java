package com.skhu.vote.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.LoginModel;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.SessionService;
import com.skhu.vote.utils.SHA512EncryptUtils;

@RestController
@RequestMapping("admin")
public class AdminController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String HEADER = "Authorization";

	@Autowired
	AdminRepository adminRepo;
	@Autowired
	SessionService sessionService;
	@Autowired
	JwtService jwtService;

	@PostMapping("signin")
	public ResponseEntity<DefaultResponse> signIn(@RequestBody LoginModel login, HttpServletResponse response) {
		DefaultResponse defResponse = new DefaultResponse();
		ADMIN admin = adminRepo.findOne(login.getId());

		if(admin == null) {
			defResponse.setMsg("입력하신 관리자가 없습니다.");
			defResponse.setStatus(StatusEnum.FAIL);
		}

		if(admin.getPassword().equals(SHA512EncryptUtils.encrypt(login.getPassword()))) {
			// 해당 계정 세션이 존재하는 경우
			if(sessionService.isSession(login.getId())) {
				defResponse.setMsg("이미 접속중.");
			}
			// 해당 계정 세션이 존재하지 않는 경우
			else {
				String token = jwtService.createToken("admin", admin);				// 토큰 생성
				response.setHeader(HEADER, token);
//				sessionService.setSession(HEADER, token);

				logger.info("createToken: {}", token);
				logger.info("isValid: {}", jwtService.isValid(token));

				sessionService.setSession(login.getId(), admin);	// 세션 생성
				logger.info("login ID: {}", sessionService.getSession(login.getId()).toString());

				if(admin.getType().equals("2")) {
					defResponse.setData(token);
					defResponse.setMsg("선관위원장 로그인 성공.");
					defResponse.setStatus(StatusEnum.SUCCESS);
				}
				else {
					defResponse.setData(token);
					defResponse.setMsg("선관위원 로그인 성공.");
					defResponse.setStatus(StatusEnum.SUCCESS);
				}
			}

			return new ResponseEntity<DefaultResponse>(defResponse, HttpStatus.OK);
		}
		// Login Fail
		else {
			defResponse.setMsg("올바른 정보를 입력하세요.");
			return new ResponseEntity<DefaultResponse>(defResponse, HttpStatus.OK);
		}
	}

	@GetMapping("signout")
	public ResponseEntity<DefaultResponse> signOut(HttpServletRequest request) {
		DefaultResponse response = new DefaultResponse();

		request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		sessionService.removeSession(jwtService.getAuthId("admin"));
        sessionService.removeSession(request.getHeader(HEADER));

        sessionService.removeSession("token");

        response.setData(jwtService.getTokenData("admin"));
        response.setStatus(StatusEnum.SUCCESS);
		response.setMsg("로그아웃 성공.");
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

}
