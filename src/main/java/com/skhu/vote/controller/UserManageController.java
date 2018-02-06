package com.skhu.vote.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skhu.vote.domain.USER;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.UserRepository;
import com.skhu.vote.service.ExcelService;
import com.skhu.vote.service.UserService;

/*
 * 유권자 명단 관리 CRD
 */

@RestController
@RequestMapping("usermanage")
public class UserManageController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	ExcelService excelService;

	// 유권자 등록 (엑셀 업로드)
	@PostMapping("upload")
	public ResponseEntity<DefaultResponse> upload(@RequestParam(value="file", required=true) MultipartFile excelFile) throws Exception {
		DefaultResponse response = new DefaultResponse();

		// MultipartFile excelFile = request.getFile("excelFile");
		if(excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀 파일을 선택해주세요.");
		}

		File destFile = new File("D:\\" + excelFile.getOriginalFilename());		// D드라이브에 업로드한 파일 저장

		try {
			excelFile.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		List<USER> users = excelService.excelUpload(destFile);

		for(USER user : users) {
			userRepo.save(user);
		}

		response.setData(users);
		response.setMsg("엑설이 업로드되었습니다.");
		response.setStatus(StatusEnum.SUCCESS);

		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

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


	/*
	 * 유권자 등록 (엑셀 업로드 구현해야함)
	 * MultipartHttpServletRequest request
	 */
//	@PostMapping("upload")
//	public ResponseEntity<DefaultResponse> upload(@RequestParam("file") MultipartFile excelFile) throws Exception {
//		DefaultResponse response = new DefaultResponse();
//		System.out.println("1번째 출력");
//
//		// MultipartFile excelFile = request.getFile("excelFile");
//		if(excelFile == null || excelFile.isEmpty()) {
//			throw new RuntimeException("엑셀 파일을 선택해주세요.");
//		}
//		File destFile = new File("D:\\" + excelFile.getOriginalFilename());
//		try {
//			excelFile.transferTo(destFile);
//		} catch (IllegalStateException | IOException e) {
//			throw new RuntimeException(e.getMessage(), e);
//		}
//
//		List<USER> users = excelService.excelUpload(destFile);
//		for(USER user : users) {
//			System.out.println(user.getName());
//			userRepo.save(user);
//		}
//
//		response.setData(users);
//		response.setMsg("엑설이 업로드되었습니다.");
//		response.setStatus(StatusEnum.SUCCESS);
//		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
//	}


}
