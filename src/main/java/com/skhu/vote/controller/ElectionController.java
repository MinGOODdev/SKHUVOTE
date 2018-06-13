package com.skhu.vote.controller;

import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.model.VoteInfoModel;
import com.skhu.vote.repository.VoteInfoRepository;
import com.skhu.vote.service.VoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
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

  /**
   * 전체 선거 목록을 조회합니다.
   *
   * @return
   */
  @GetMapping("list")
  public ResponseEntity<DefaultResponse> voteList() {
    DefaultResponse response = new DefaultResponse();
    response.setData(voteInfoService.findAll());
    response.setMsg("선거 목록.");
    response.setStatus(StatusEnum.SUCCESS);
    return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
  }

  /**
   * 선거별 세부 정보를 조회합니다.
   *
   * @param voteId
   * @return
   */
  @GetMapping("list/{voteId}")
  public ResponseEntity<DefaultResponse> voteDetailList(@PathVariable int voteId) {
    DefaultResponse response = new DefaultResponse();
    response.setData(voteInfoRepo.findByVoteId(voteId));
    response.setMsg("선거별 세부 정보.");
    response.setStatus(StatusEnum.SUCCESS);
    return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
  }

  /**
   * 선거를 등록합니다.
   *
   * @param v
   * @return
   */
  @PostMapping("")
  public ResponseEntity<DefaultResponse> voteCreate(@RequestBody VoteInfoModel v) {
    DefaultResponse response = new DefaultResponse();
    response.setData(v);
    response.setMsg("선거 등록 성공.");
    response.setStatus(StatusEnum.SUCCESS);
    voteInfoService.insertVoteInfo(v);
    return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
  }

  /**
   * 선거를 수정합니다.
   *
   * @param voteId
   * @param v
   * @return
   */
  @Transactional
  @PutMapping("{voteId}")
  public ResponseEntity<DefaultResponse> voteUpdate(@PathVariable int voteId, @RequestBody VoteInfoModel v) {
    DefaultResponse response = new DefaultResponse();

    if (voteInfoService.findOne(voteId) == null) {
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

  /**
   * 해당 선거를 제거합니다.
   *
   * @param voteId
   * @return
   */
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
