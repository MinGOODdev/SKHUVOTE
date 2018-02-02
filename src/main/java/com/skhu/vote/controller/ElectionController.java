package com.skhu.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.model.VoteInfoModel;
import com.skhu.vote.service.VoteInfoService;

/*
 * 선거 등록 (이름, 시간, 대상) CRD
 * 시간 제한 처리 ?
 */

@RestController
@RequestMapping("election")
public class ElectionController {

	@Autowired
	VoteInfoService voteInfoService;

	// 선거 목록 조회
	@GetMapping("list")
	public ResponseEntity<DefaultResponse> voteList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(voteInfoService.findAll());
		response.setMsg("선거 목록입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 선거 등록 (GET)
	@GetMapping("create")
	public ResponseEntity<DefaultResponse> voteCreate() {
		DefaultResponse response = new DefaultResponse();
		response.setData(new VoteInfoModel());
		response.setMsg("선거를 등록하려 합니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 선거 등록 (POST)
	@PostMapping("create")
	public ResponseEntity<DefaultResponse> voteCreate(@RequestBody VoteInfoModel v) {
		DefaultResponse response = new DefaultResponse();
		response.setData(v);
		response.setMsg("선거를 등록했습니다.");
		response.setStatus(StatusEnum.SUCCESS);
		voteInfoService.insertVoteInfo(v);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 선거 삭제
	@DeleteMapping("{id}")
	public ResponseEntity<DefaultResponse> voteDelete(@PathVariable int voteId) {
		DefaultResponse response = new DefaultResponse();
		if (voteInfoService.findOne(voteId) == null) {
			response.setMsg("해당 선거 정보가 존재하지 않습니다.");
			response.setStatus(StatusEnum.FAIL);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
		} else {
			response.setMsg("해당 선거가 삭제되었습니다.");
			response.setStatus(StatusEnum.SUCCESS);
			response.setData(voteInfoService.findOne(voteId));
			voteInfoService.delete(voteId);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
	}

}
