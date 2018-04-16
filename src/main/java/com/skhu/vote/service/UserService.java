package com.skhu.vote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skhu.vote.domain.USER;
import com.skhu.vote.repository.UserRepository;

@Service
public class UserService {

	private static int allCount = 0;

	@Autowired
	private UserRepository userRepo;

	public List<USER> findAll() {
		return userRepo.findAll();
	}

	public USER findOne(String id) {
		return userRepo.findOne(id);
	}

	public void delete(String id) {
		userRepo.delete(id);
	}

	public List<USER> findByDepartmentDepartmentId(int target) {
		return userRepo.findByDepartmentDepartmentId(target);
	}

	public int countByDepartmentDepartmentId(int target) {
		if(target == 1) {			// 전체
			allCount += findAll().size();
		}
		else if(target == 10) {		// 인문융합자율학부
			for(int i = target; i < target + 10; ++i)
				allCount += findByDepartmentDepartmentId(i).size();
		}
		else if(target == 11) {		// 인문융합 영어영문
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 12) {		// 인문융합 일어일본
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 13) {		// 인문융합 중어중국
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 14) {		// 인문융합 기독교문화
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 15) {		// 인문융합 혁신융합
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 20) {		// 사회융합자율학부
			for(int i = target; i < target + 10; ++i)
				allCount += findByDepartmentDepartmentId(i).size();
		}
		else if(target == 21) {		// 사회융합 사회과학
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 22) {		// 사회융합 사회복지
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 23) {		// 사회융합 경영
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 24) {		// 사회융합 혁신융합
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 30) {		// 미디어컨텐츠융합자율학부
			for(int i = target; i < target + 10; ++i)
				allCount += findByDepartmentDepartmentId(i).size();
		}
		else if(target == 31) {		// 미컨 신문방송
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 32) {		// 미컨 디지털컨텐츠
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 33) {		// 미컨 혁신융합
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 40) {		// IT융합자율학부
			for(int i = target; i < target + 10; ++i)
				allCount += findByDepartmentDepartmentId(i).size();
		}
		else if(target == 41) {		// IT 컴퓨터
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 42) {		// IT 소프트웨어
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 43) {		// IT 정보통신
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 44) {		// IT 글로컬IT
			allCount += findByDepartmentDepartmentId(target).size();
		}
		else if(target == 45) {		// IT 혁신융합
			allCount += findByDepartmentDepartmentId(target).size();
		}
		return allCount;
	}

}
