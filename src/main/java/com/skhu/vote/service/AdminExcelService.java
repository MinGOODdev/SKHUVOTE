package com.skhu.vote.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.utils.ExcelRead;
import com.skhu.vote.utils.ExcelReadOption;
import com.skhu.vote.utils.SHA512EncryptUtils;

@Service
public class AdminExcelService {

  public List<ADMIN> excelUpload(java.io.File destFile) throws Exception {
    ExcelReadOption excelReadOption = new ExcelReadOption();
    excelReadOption.setFilePath(destFile.getAbsolutePath());
    // A: id, B: name, C: password, D: departmentName, E: type
    excelReadOption.setOutputCols("A", "B", "C", "D", "E");
    excelReadOption.setStartRow(1);

    List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption);

    List<ADMIN> admins = new ArrayList<>();
    for (Map<String, String> content : excelContent) {
      ADMIN admin = new ADMIN();

      admin.setId(content.get("A"));
      admin.setName(content.get("B"));
      admin.setPassword(SHA512EncryptUtils.encrypt(content.get("C")));
      admin.setDepartmentName(content.get("D"));
      admin.setType(content.get("E"));
      admin.setLastLogin(new Date());
      admins.add(admin);
    }
    return admins;
  }

}
