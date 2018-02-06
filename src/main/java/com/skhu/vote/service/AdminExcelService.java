package com.skhu.vote.service;

import java.util.ArrayList;
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
		excelReadOption.setOutputCols("A", "B", "C", "D", "E");
		// A: id, B: name, C: password, D: departmentName, E: type
		excelReadOption.setStartRow(1);

		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption);

		List<ADMIN> admins = new ArrayList<>();
		for(Map<String, String> content : excelContent) {
			ADMIN admin = new ADMIN();

			admin.setId(content.get("A"));
			admin.setName(content.get("B"));
			admin.setPassword(SHA512EncryptUtils.encrypt(content.get("C")));	// 비밀번호 암호화
			admin.setDepartmentName(content.get("D"));
			admin.setType(content.get("E"));
			admins.add(admin);
		}
		return admins;
	}

}
