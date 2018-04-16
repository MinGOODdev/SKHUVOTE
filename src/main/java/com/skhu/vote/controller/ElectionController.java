package com.skhu.vote.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.model.VoteInfoModel;
import com.skhu.vote.repository.VoteInfoRepository;
import com.skhu.vote.service.VoteInfoService;

/*
 * Election Registration (voteName, startTime, endTime, target) CRD
 * startTime, endTime Restrict
 * 선거 등록 (선거 이름, 시작 시각, 종료 시각, 대상 학과)
 * 시작 시각, 종료 시각 제한 걸기
 */

@RestController
@RequestMapping("election")
public class ElectionController {

	@Autowired
	private VoteInfoService voteInfoService;
	@Autowired
	private VoteInfoRepository voteInfoRepo;

	// All Election List
	// 전체 선거 목록
	@GetMapping("list")
	public ResponseEntity<DefaultResponse> voteList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(voteInfoService.findAll());
		response.setMsg("선거 목록.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// Selected Election's Detail Information
	// 선거별 세부 정보
	@GetMapping("list/{voteId}")
	public ResponseEntity<DefaultResponse> voteDetailList(@PathVariable int voteId) {
		DefaultResponse response = new DefaultResponse();
		response.setData(voteInfoRepo.findByVoteId(voteId));
		response.setMsg("선거별 세부 정보.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// Election Registration (POST)
	// 선거 등록 (POST)
	@PostMapping("")
	public ResponseEntity<DefaultResponse> voteCreate(@RequestBody VoteInfoModel v) {
		DefaultResponse response = new DefaultResponse();
		response.setData(v);
		response.setMsg("선거 등록 성공.");
		response.setStatus(StatusEnum.SUCCESS);
		voteInfoService.insertVoteInfo(v);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// Election Update(POST)
	// 등록된 선거 수정
	@Transactional
	@PutMapping("{voteId}")
	public ResponseEntity<DefaultResponse> voteUpdate(@PathVariable int voteId, @RequestBody VoteInfoModel v) {
		DefaultResponse response = new DefaultResponse();
		if(voteInfoService.findOne(voteId) == null) {
			response.setMsg("해당 선거 정보가 존재하지 않습니다.");
			response.setStatus(StatusEnum.FAIL);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
		else {
			response.setData(v);
			response.setMsg("선거 수정 성공.");
			response.setStatus(StatusEnum.SUCCESS);
			voteInfoService.update(v);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
	}

	// Election Delete
	// 선거 삭제
	@Transactional
	@DeleteMapping("{voteId}")
	public ResponseEntity<DefaultResponse> voteDelete(@PathVariable int voteId) {
		DefaultResponse response = new DefaultResponse();
		if (voteInfoService.findOne(voteId) == null) {
			response.setMsg("해당 선거 정보가 존재하지 않습니다.");
			response.setStatus(StatusEnum.FAIL);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
		else {
			response.setMsg("해당 선거 삭제 성공.");
			response.setStatus(StatusEnum.SUCCESS);
			response.setData(voteInfoService.findOne(voteId));
			voteInfoService.delete(voteId);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
	}

}
