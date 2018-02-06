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

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.service.AdminExcelService;
import com.skhu.vote.service.AdminService;

/*
 * Election Organization Management CRD
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

	// Election Organization List
	@GetMapping("list")
	public ResponseEntity<DefaultResponse> userList() {
		DefaultResponse response = new DefaultResponse();
		response.setData(adminService.findAll());
		response.setMsg("선관위 목록입니다.");
		response.setStatus(StatusEnum.SUCCESS);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

	// Election Organization Member Delete
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

	// Election Organization Member Registration (Excel Upload)
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

		List<ADMIN> admins = excelService.excelUpload(destFile);

		for(ADMIN admin : admins) {
			adminRepo.save(admin);
		}

		response.setData(admins);
		response.setMsg("엑셀이 업로드되었습니다.");
		response.setStatus(StatusEnum.SUCCESS);

		return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
	}

}
