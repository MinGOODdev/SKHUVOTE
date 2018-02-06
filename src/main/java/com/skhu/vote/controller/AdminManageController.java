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

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.service.AdminExcelService;
import com.skhu.vote.service.AdminService;

/*
 * 선관위 명단 관리 CRD
 */

@RestController
@RequestMapping("adminmanage")
public class AdminManageController {

	@Autowired
	AdminExcelService excelService;
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	AdminService adminService;

	// 선관위 목록 조회
	@GetMapping("list")
	public ResponseEntity<DefaultResponse> userList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(adminService.findAll());
		response.setMsg("선관위 목록입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// 선관위 삭제
	@DeleteMapping("{id}")
	public ResponseEntity<DefaultResponse> userDelete(@PathVariable String id) {
		DefaultResponse response = new DefaultResponse();

		if (adminService.findOne(id) == null) {
			response.setMsg("해당 선관위는 존재하지 않습니다.");
			response.setStatus(StatusEnum.FAIL);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
		} else {
			response.setMsg("해당 선관위가 삭제되었습니다.");
			response.setStatus(StatusEnum.SUCCESS);
			response.setData(adminService.findOne(id));
			adminService.delete(id);
			return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
		}
	}

	// 선관위 등록 (엑셀 업로드)
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

		List<ADMIN> admins = excelService.excelUpload(destFile);

		for(ADMIN admin : admins) {
			adminRepo.save(admin);
		}

		response.setData(admins);
		response.setMsg("엑설이 업로드되었습니다.");
		response.setStatus(StatusEnum.SUCCESS);

		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

}
