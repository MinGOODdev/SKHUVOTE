package com.skhu.vote.controller;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.model.DefaultResponse;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.repository.AdminRepository;
import com.skhu.vote.service.AdminExcelService;
import com.skhu.vote.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Election Organization Management CRD
 * 선관위 관리 CRD
 */
@RestController
@RequestMapping("adminmanage")
public class AdminManageController {

  @Autowired
  private AdminExcelService excelService;

  @Autowired
  private AdminRepository adminRepo;

  @Autowired
  private AdminService adminService;

  /**
   * 선관위 전체 목록을 조회합니다.
   * @return
   */
  @GetMapping("list")
  public ResponseEntity<DefaultResponse> userList() {
    DefaultResponse response = new DefaultResponse();
    response.setData(adminService.findAll());
    response.setMsg("선관위 목록.");
    response.setStatus(StatusEnum.SUCCESS);
    return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
  }

  /**
   * 해당 선관위를 제거합니다.
   *
   * @param id
   * @return
   */
  @DeleteMapping("{id}")
  public ResponseEntity<DefaultResponse> userDelete(@PathVariable String id) {
    DefaultResponse response = new DefaultResponse();

    if (adminService.findOne(id) == null) {
      response.setMsg("해당 선관위가 존재하지 않습니다.");
      response.setStatus(StatusEnum.FAIL);
      return new ResponseEntity<DefaultResponse>(response, HttpStatus.NO_CONTENT);
    }
    else {
      response.setMsg("해당 선관위가 삭제되었습니다.");
      response.setStatus(StatusEnum.SUCCESS);
      response.setData(adminService.findOne(id));

      adminService.delete(id);
      return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
    }
  }

  /**
   * 엑셀 파일로 선관위를 등록합니다.
   *
   * @param excelFile
   * @param request
   * @return
   * @throws Exception
   */
  @PostMapping("upload")
  public ResponseEntity<DefaultResponse> upload(@RequestParam(value = "file", required = true) MultipartFile excelFile, HttpServletRequest request) throws Exception {
    DefaultResponse response = new DefaultResponse();

    // MultipartFile excelFile = request.getFile("excelFile");
    if (excelFile == null || excelFile.isEmpty()) {
      throw new RuntimeException("엑셀 파일을 선택해주세요.");
    }

    java.io.File destFile = new java.io.File(request.getSession().getServletContext().getRealPath("resources") + "\\" + excelFile.getOriginalFilename());
    // File destFile = new File("D:\\" + excelFile.getOriginalFilename());

    try {
      excelFile.transferTo(destFile);
    } catch (IllegalStateException | IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }

    List<ADMIN> admins = excelService.excelUpload(destFile);
    for (ADMIN admin : admins) {
      adminRepo.save(admin);
    }

    response.setData(admins);
    response.setMsg("엑셀 업로드 성공");
    response.setStatus(StatusEnum.SUCCESS);
    return new ResponseEntity<DefaultResponse>(response, HttpStatus.OK);
  }

}
