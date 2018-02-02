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

import com.skhu.vote.model.CandidateModel;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.model.VoteInfoModel;
import com.skhu.vote.service.CandidateService;
import com.skhu.vote.service.UserService;
import com.skhu.vote.service.VoteInfoService;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	UserService userService;
	@Autowired
	VoteInfoService voteInfoService;
	@Autowired
	CandidateService candidateService;

	// 유권자 목록 조회
	@GetMapping("user/list")
	public ResponseEntity<DefaultResponse> userList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(userService.findAll());
		response.setMsg("유권자 목록입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	/*
	 * 유권자 등록 (엑셀 업로드 구현해야함)
	 */

	// 유권자 삭제
	@DeleteMapping("user/{id}")
	public ResponseEntity<DefaultResponse> userDelete(@PathVariable String id) {
		DefaultResponse response = new DefaultResponse();

		if(userService.findOne(id) == null) {
			response.setMsg("해당 유권자는 존재하지 않습니다.");
			response.setStatus(StatusEnum.FAIL);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
		}
		else {
			response.setMsg("해당 유권자가 삭제되었습니다.");
			response.setStatus(StatusEnum.SUCCESS);
			response.setData(userService.findOne(id));
			userService.delete(id);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
	}

	// 선거 목록 조회
	@GetMapping("vote/list")
	public ResponseEntity<DefaultResponse> voteList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(voteInfoService.findAll());
		response.setMsg("선거 목록입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 선거 등록 (GET)
	@GetMapping("vote/create")
	public ResponseEntity<DefaultResponse> voteCreate() {
		DefaultResponse response = new DefaultResponse();
		response.setData(new VoteInfoModel());
		response.setMsg("선거를 등록하려 합니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 선거 등록 (POST)
	@PostMapping("vote/create")
	public ResponseEntity<DefaultResponse> voteCreate(@RequestBody VoteInfoModel v) {
		DefaultResponse response = new DefaultResponse();
		response.setData(v);
		response.setMsg("선거를 등록했습니다.");
		response.setStatus(StatusEnum.SUCCESS);
		voteInfoService.insertVoteInfo(v);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 선거 삭제
	@DeleteMapping("vote/{id}")
	public ResponseEntity<DefaultResponse> voteDelete(@PathVariable int id) {
		DefaultResponse response = new DefaultResponse();
		if(voteInfoService.findOne(id) == null) {
			response.setMsg("해당 선거 정보가 존재하지 않습니다.");
			response.setStatus(StatusEnum.FAIL);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
		}
		else {
			response.setMsg("해당 선거가 삭제되었습니다.");
			response.setStatus(StatusEnum.SUCCESS);
			response.setData(voteInfoService.findOne(id));
			voteInfoService.delete(id);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
	}

	// 후보자 목록 조회
	@GetMapping("candidate/list")
	public ResponseEntity<DefaultResponse> candidateList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(candidateService.findAll());
		response.setMsg("후보자 목록입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}


	// 후보자 등록 (GET)
	@GetMapping("candidate/create/{voteId}")
	public ResponseEntity<DefaultResponse> candidateCreate(@PathVariable int voteId) {
		DefaultResponse response = new DefaultResponse();
		response.setData(new CandidateModel());
		response.setMsg("후보자를 등록하려 합니다,");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 후보자 등록 (POST)
	@PostMapping("candidate/create/{voteId}")
	public ResponseEntity<DefaultResponse> candidateCreate(@RequestBody CandidateModel c, @PathVariable int voteId) {
		DefaultResponse response = new DefaultResponse();
		candidateService.insertCandidate(c, voteId);
		response.setData(c);
		response.setMsg("후보자를 등록했습니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

//	// 후보자 삭제
//	@GetMapping("candidate/delete/{candidateId}")


}
