package com.skhu.vote.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skhu.vote.model.DefaultResponse;

@RestController
@RequestMapping("admin")
public class AdminController {

	@PostMapping("signin")
	public ResponseEntity<DefaultResponse> signin(String id, String name, int type, Date lastLogin, HttpServletResponse response) {
		DefaultResponse defResponse = new DefaultResponse();

	}

}
