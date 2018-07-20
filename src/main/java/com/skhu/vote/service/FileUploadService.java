package com.skhu.vote.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private S3Service s3Service;

  public String fileUpload(MultipartFile uploadFile) throws IOException {

    logger.info("fileUpload 진입");

    String fileName = uploadFile.getOriginalFilename();
    String url;

    logger.info("fileUpload try문 이전");
    try {
      // 확장자
      String ext = fileName.substring(fileName.lastIndexOf("."));
      // 파일 이름 암호화
      String saveName = getUuid() + ext;
      // 파일 객체 생성
      File file = new File(System.getProperty("user.dir") + saveName);
      // 파일 변환
      uploadFile.transferTo(file);
      // S3 파일 업로드
      s3Service.uploadOnS3(saveName, file);
      url = "https://s3.ap-northeast-2.amazonaws.com/skhuvote/" + saveName;
      // 파일 삭제
      file.delete();
    } catch (StringIndexOutOfBoundsException e) {
      // 파일이 없을 경우 예외 처리
      url = null;
    }
    return url;
  }

  public String getUuid() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
