package com.skhu.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@Autowired
	AdminRepository adminRepo;
	@Autowired
	SessionService sessionService;
	@Autowired
	JwtService jwtService;

	@PostMapping("signin")
	public ResponseEntity<DefaultResponse> signIn(@RequestBody LoginModel login) {
		DefaultResponse defResponse = new DefaultResponse();
		ADMIN admin = adminRepo.findOne(login.getId());
		
		if(admin == null) {
			defResponse.setMsg("입력하신 관리자가 없습니다.");
			defResponse.setStatus(StatusEnum.FAIL);
		}
		
		if(admin.getPassword().equals(SHA512EncryptUtils.encrypt(login.getPassword()))) {
			jwtService.createToken("admin", admin);
			System.out.println("**************************"+jwtService.createToken("admin", admin));
			System.out.println("**************************"+jwtService.isValid(jwtService.createToken("admin", admin)));
			
			// 세션이 없다면 세션 생성
			if(!sessionService.isSession(login.getId())) {
				sessionService.setSession(login.getId(), admin);
				System.out.println(sessionService.getSession(login.getId()).toString());
			}
			
			if(admin.getType().equals("2")) {
				defResponse.setData(admin);
				defResponse.setMsg("선관위원장으로 로그인하셨습니다.");
				defResponse.setStatus(StatusEnum.SUCCESS);
			}
			else {
				defResponse.setData(admin);
				defResponse.setMsg("선관위원으로 로그인하셨습니다.");
				defResponse.setStatus(StatusEnum.SUCCESS);
			}
			return new ResponseEntity<DefaultResponse>(defResponse, HttpStatus.OK);
		}
		else {
			defResponse.setMsg("올바른 정보를 입력하세요.");
			return new ResponseEntity<DefaultResponse>(defResponse, HttpStatus.OK);
		}
	}
	
//	@GetMapping("signout")
//	public ResponseEntity<DefaultResponse> signOut() {
//		
//	}

}
