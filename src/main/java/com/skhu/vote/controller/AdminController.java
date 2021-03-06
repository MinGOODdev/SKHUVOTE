package com.skhu.vote.controller;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.LoginModel;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.service.JwtService;
import com.skhu.vote.service.SessionService;
import com.skhu.vote.utils.SHA512EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("admin")
public class AdminController {

  private Logger logger = LoggerFactory.getLogger(getClass());
  private static final String HEADER = "Authorization";

  @Autowired
  private AdminRepository adminRepo;

  @Autowired
  private SessionService sessionService;

  @Autowired
  private JwtService jwtService;

  /**
   * 로그인
   *
   * @param login
   * @param response
   * @return
   */
  @PostMapping("signin")
  public ResponseEntity<DefaultResponse> signIn(@RequestBody LoginModel login, HttpServletResponse response) {
    DefaultResponse defResponse = new DefaultResponse();
    ADMIN admin = adminRepo.findOne(login.getId());

    if (admin == null) {
      defResponse.setMsg("입력하신 관리자가 존재하지 않습니다.");
      defResponse.setStatus(StatusEnum.FAIL);
    }

    if (admin.getPassword().equals(SHA512EncryptUtils.encrypt(login.getPassword()))) {
      // 해당 계정 세션이 존재하는 경우
      if (sessionService.isSession(login.getId())) {
        defResponse.setMsg("해당 ID로 이미 접속중 입니다.");
      }
      // 해당 계정 세션이 존재하지 않는 경우
      else {
        String token = jwtService.createToken("admin", admin);        // 토큰 생성
        response.setHeader(HEADER, token);

        sessionService.setSession(login.getId(), admin);                   // 세션 생성

        if (admin.getType().equals("2")) {
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
    // 로그인 실패
    else {
      defResponse.setMsg("올바른 정보를 입력하세요.");
      return new ResponseEntity<DefaultResponse>(defResponse, HttpStatus.OK);
    }
  }

  /**
   * 로그아웃
   *
   * @param request
   * @return
   */
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
