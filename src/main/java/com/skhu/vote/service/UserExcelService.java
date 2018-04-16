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
	private UserRepository userRepo;
	@Autowired
	private DepartmentRepository depRepo;

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

	private int department(String depName) {
		int depId = 0;
		switch(depName) {
			case "인문융합자율학부": depId = 10; break;
			case "인문융합자율학부 영어학전공": depId = 11; break;
			case "인문융합자율학부 일어일본학전공": depId = 12; break;
			case "인문융합자율학부 중어중국학전공": depId = 13; break;
			case "인문융합자율학부 기독교문화전공": depId = 14; break;
			case "인문융합자율학부 혁신융합전공": depId = 15; break;
			case "사회융합자율학부": depId = 20; break;
			case "사회융합자율학부 사회과학전공": depId = 21; break;
			case "사회융합자율학부 사회복지학전공": depId = 22; break;
			case "사회융합자율학부 경영학전공": depId = 23; break;
			case "사회융합자율학부 혁신융합전공": depId = 24; break;
			case "미디어컨텐츠융합자율학부": depId = 30; break;
			case "미디어컨텐츠융합자율학부 신문방송학전공": depId = 31; break;
			case "미디어컨텐츠융합자율학부 디지털컨텐츠전공": depId = 32; break;
			case "미디어컨텐츠융합자율학부 혁신융합전공": depId = 33; break;
			case "IT융합자율학부": depId = 40; break;
			case "IT융합자율학부 컴퓨터공학전공": depId = 41; break;
			case "IT융합자율학부 소프트웨어공학전공": depId = 42; break;
			case "IT융합자율학부 정보통신공학전공": depId = 43; break;
			case "IT융합자율학부 글로컬IT전공": depId = 44; break;
			case "IT융합자율학부 혁신융합전공": depId = 45; break;
			case "테스트": depId = 50; break;
		}
		return depId;
	}
}
