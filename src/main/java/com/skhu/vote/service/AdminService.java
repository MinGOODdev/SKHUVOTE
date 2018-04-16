package com.skhu.vote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skhu.vote.domain.ADMIN;
import com.skhu.vote.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;

	public List<ADMIN> findAll() {
		return adminRepo.findAll();
	}

	public ADMIN findOne(String id) {
		return adminRepo.findOne(id);
	}

	public void delete(String id) {
		adminRepo.delete(id);
	}

}
