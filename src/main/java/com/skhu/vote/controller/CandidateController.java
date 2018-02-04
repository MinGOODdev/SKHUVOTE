package com.skhu.vote.controller;

import java.util.List;

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

import com.skhu.vote.domain.CANDIDATE;
import com.skhu.vote.model.CandidateModel;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.CandidateRepository;
import com.skhu.vote.service.CandidateService;

/*
 * 후보자 관리 CRD
 */

@RestController
@RequestMapping("candidate")
public class CandidateController {
	
	@Autowired
	CandidateService candidateService;
	@Autowired
	CandidateRepository candidateRepo;

	// 후보자 전체 목록 조회
	@GetMapping("list")
	public ResponseEntity<DefaultResponse> candidateList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(candidateService.findAll());
		response.setMsg("후보자 목록입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}
	
	// 각 선거별 후보자 목록 조회
	@GetMapping("list/{voteId}")
	public ResponseEntity<DefaultResponse> candidateVoteList(@PathVariable int voteId) {
		DefaultResponse response = new DefaultResponse();
		response.setData(candidateRepo.findByVoteInfoVoteId(voteId));
		response.setMsg("해당 선거별 후보자 목록입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 후보자 등록 (GET)
	// 필요없다면 remove
	@GetMapping("{voteId}")
	public ResponseEntity<DefaultResponse> candidateCreate(@PathVariable int voteId) {
		DefaultResponse response = new DefaultResponse();
		response.setData(new CandidateModel());
		response.setMsg("후보자를 등록하려 합니다,");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 후보자 등록 (POST)
	@PostMapping("{voteId}")
	public ResponseEntity<DefaultResponse> candidateCreate(@RequestBody CandidateModel c, @PathVariable int voteId) {
		DefaultResponse response = new DefaultResponse();
		
		List<CANDIDATE> list = candidateService.insertCandidate(c, voteId);
		response.setData(list);
		response.setMsg("후보자를 등록했습니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 후보자 삭제
//	@DeleteMapping("{candidateId}")
//	public ResponseEntity<DefaultResponse> candidateDelete(@PathVariable int candidateId) {
//		DefaultResponse response = new DefaultResponse();
//		
//		if (candidateService.findOne(candidateId) == null) {
//			response.setMsg("해당 유권자는 존재하지 않습니다.");
//			response.setStatus(StatusEnum.FAIL);
//			return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
//		} else {
//			response.setMsg("해당 유권자가 삭제되었습니다.");
//			response.setStatus(StatusEnum.SUCCESS);
//			response.setData(candidateService.findOne(candidateId));
//			candidateService.delete(candidateId);
//			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
//		}
//	}
	
	@DeleteMapping("{voteId}/{candidateId}")
	public ResponseEntity<DefaultResponse> candidateDelete(@PathVariable int voteId, @PathVariable int candidateId) {
		DefaultResponse response = new DefaultResponse();
		
		if (candidateService.findOne(candidateId) == null) {
			response.setMsg("해당 유권자는 존재하지 않습니다.");
			response.setStatus(StatusEnum.FAIL);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
		} else {
			response.setMsg("해당 유권자가 삭제되었습니다.");
			response.setStatus(StatusEnum.SUCCESS);
			response.setData(candidateService.findOne(candidateId));
			candidateService.delete(voteId, candidateId);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
	}

}
