package com.skhu.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.skhu.vote.service.UserExcelService;
import com.skhu.vote.service.UserService;

/*
 * Voter CRD
 * 유권자 CRD
 */

@RestController
@RequestMapping("usermanage")
public class UserManageController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserExcelService excelService;

	// Voter Registration (Excel Upload)
	// 유권자 등록 (엑셀 업로드)
	@PostMapping("upload")
	public ResponseEntity<DefaultResponse> upload(@RequestParam(value="file", required=true) MultipartFile excelFile, HttpServletRequest request) throws Exception {
		DefaultResponse response = new DefaultResponse();

		// MultipartFile excelFile = request.getFile("excelFile");
		if(excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀 파일을 선택해주세요.");
		}

		java.io.File destFile = new java.io.File(request.getSession().getServletContext().getRealPath("resources")+"\\"+excelFile.getOriginalFilename());
		// File destFile = new File("D:\\" + excelFile.getOriginalFilename());

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
		response.setMsg("엑셀 업로드 성공.");
		response.setStatus(StatusEnum.SUCCESS);

		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// Voter List
	// 유권자 목록
	@GetMapping("list")
	public ResponseEntity<DefaultResponse> userList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(userService.findAll());
		response.setMsg("유권자 목록.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// Voter Delete
	// 유권자 삭제
	@DeleteMapping("{id}")
	public ResponseEntity<DefaultResponse> userDelete(@PathVariable String id) {
		DefaultResponse response = new DefaultResponse();

		if (userService.findOne(id) == null) {
			response.setMsg("해당 유권자는 존재하지 않습니다.");
			response.setStatus(StatusEnum.FAIL);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
		} else {
			response.setMsg("해당 유권자 삭제 성공.");
			response.setStatus(StatusEnum.SUCCESS);
			response.setData(userService.findOne(id));
			userService.delete(id);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
	}

	/*
	 * Voter Registration (Excel Upload Fail...)
	 * MultipartHttpServletRequest request
	 * 유권자 등록 (엑셀 업로드 안됨..)
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
