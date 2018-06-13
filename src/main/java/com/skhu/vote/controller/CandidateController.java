package com.skhu.vote.controller;

import com.skhu.vote.domain.CANDIDATE;
import com.skhu.vote.model.CandidateModel;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.CandidateRepository;
import com.skhu.vote.service.CandidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Candidate Management CRD
 * 후보자 관리 CRD
 */
@RestController
@RequestMapping("candidate")
public class CandidateController {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CandidateService candidateService;

  /**
   * 전체 후보자 목록을 조회합니다.
   *
   * @return
   */
  @GetMapping("list")
  public ResponseEntity<DefaultResponse> candidateList() {
    DefaultResponse response = new DefaultResponse();
    response.setData(candidateService.findAll());
    response.setMsg("후보자 목록.");
    response.setStatus(StatusEnum.SUCCESS);
    return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
  }

  /**
   * 후보자를 등록합니다.
   *
   * @param c
   * @param voteId
   * @return
   */
  @PostMapping("{voteId}")
  public ResponseEntity<DefaultResponse> candidateCreate(@RequestBody CandidateModel c, @PathVariable int voteId) {
    DefaultResponse response = new DefaultResponse();
    List<CANDIDATE> list = candidateService.insertCandidate(c, voteId);

    response.setData(list);
    response.setMsg("후보자 등록 성공.");
    response.setStatus(StatusEnum.SUCCESS);
    return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
  }

  /**
   * 선거별 후보자 개별 삭제
   *
   * @param voteId
   * @param candidateId
   * @return
   */
  @DeleteMapping("{voteId}/{candidateId}")
  public ResponseEntity<DefaultResponse> candidateDelete(@PathVariable int voteId, @PathVariable int candidateId) {
    DefaultResponse response = new DefaultResponse();

    if (candidateService.findOne(candidateId) == null) {
      response.setMsg("해당 유권자가 존재하지 않습니다.");
      response.setStatus(StatusEnum.FAIL);
      return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
    }
    else {
      response.setMsg("해당 유권자 삭제 성공.");
      response.setStatus(StatusEnum.SUCCESS);
      response.setData(candidateService.findOne(candidateId));

      candidateService.delete(voteId, candidateId);
      return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }
  }

  // // Candidate Registration (POST) 원본 (이미지 업로드 연구 해보기)
  // // 후보자 등록 (POST)
  // // @ResponseBody
  // @PostMapping("{voteId}")
  // public ResponseEntity<DefaultResponse> candidateCreate(@RequestBody
  // CandidateModel c, @PathVariable int voteId) throws IOException {
  // // logger.info("multipart: {}", c.getPhoto().toString());
  // logger.info("everything: {}", c.getCampName());
  // DefaultResponse response = new DefaultResponse();
  // List<CANDIDATE> list = candidateService.insertCandidate(c, voteId);
  // response.setData(list);
  // response.setMsg("후보자 등록 성공.");
  // response.setStatus(StatusEnum.SUCCESS);
  // return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
  // }

}
