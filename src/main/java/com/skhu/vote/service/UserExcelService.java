package com.skhu.vote.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skhu.vote.domain.DEPARTMENT;
import com.skhu.vote.domain.USER;
import com.skhu.vote.repository.DepartmentRepository;
import com.skhu.vote.repository.UserRepository;
import com.skhu.vote.utils.ExcelRead;
import com.skhu.vote.utils.ExcelReadOption;

@Service
public class UserExcelService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	DepartmentRepository depRepo;

	public List<USER> excelUpload(java.io.File destFile) throws Exception {
		ExcelReadOption excelReadOption = new ExcelReadOption();
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		// A: id, B: name, C: departmentId, D: tel
		excelReadOption.setOutputCols("A", "B", "C", "D");
		excelReadOption.setStartRow(1);

		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption);

		List<USER> users = new ArrayList<>();
		for(Map<String, String> content : excelContent) {
			USER user = new USER();
			DEPARTMENT department = new DEPARTMENT();
			department = depRepo.findOne(department(content.get("C")));

			user.setId(content.get("A"));
			user.setName(content.get("B"));
			user.setDepartment(department);
			user.setUserType(1);
			user.setTel(content.get("D"));
			users.add(user);
		}
		return users;
	}

	// 실제 사용시엔 종류별로 다 추가해야 한다.
	// real use -> more add..
	private int department(String depName) {
		int depId = 0;
		switch(depName) {
			case "인문융합자율학부": depId = 10; break;
			case "사회융합자율학부": depId = 20; break;
			case "미디어컨텐츠융합자율학부": depId = 30; break;
			case "IT융합자율학부": depId = 40; break;
			case "테스트": depId = 50; break;
		}
		return depId;
	}
}
