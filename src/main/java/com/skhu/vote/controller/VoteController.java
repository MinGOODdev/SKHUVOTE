package com.skhu.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.service.VoteInfoService;

/*
 * 실시간 투표율 R
 * 
 * 각 후보별 득표수는 어디에 저장되는거지 ??
 */

@RestController
@RequestMapping("vote")
public class VoteController {

	@Autowired
	VoteInfoService voteInfoService;
	
	@GetMapping("rate/{voteId}")
	public ResponseEntity<DefaultResponse> voteRate(@PathVariable int voteId) {
		DefaultResponse response = new DefaultResponse();
		response.setData(voteInfoService.voteRate(voteId));
		response.setMsg("실시간 투표율입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}
}
