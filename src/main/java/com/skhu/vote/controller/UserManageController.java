package com.skhu.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.service.UserService;

/*
 * 유권자 명단 관리 CRD
 */

@RestController
@RequestMapping("usermanage")
public class UserManageController {

	@Autowired
	UserService userService;
	
	/*
	 * 유권자 등록 (엑셀 업로드 구현해야함)
	 */

	// 유권자 목록 조회
	@GetMapping("list")
	public ResponseEntity<DefaultResponse> userList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(userService.findAll());
		response.setMsg("유권자 목록입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 유권자 삭제
	@DeleteMapping("{id}")
	public ResponseEntity<DefaultResponse> userDelete(@PathVariable String id) {
		DefaultResponse response = new DefaultResponse();

		if (userService.findOne(id) == null) {
			response.setMsg("해당 유권자는 존재하지 않습니다.");
			response.setStatus(StatusEnum.FAIL);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
		} else {
			response.setMsg("해당 유권자가 삭제되었습니다.");
			response.setStatus(StatusEnum.SUCCESS);
			response.setData(userService.findOne(id));
			userService.delete(id);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
	}

}
