package com.skhu.vote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skhu.vote.domain.USER;
import com.skhu.vote.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public List<USER> findAll() {
		return userRepo.findAll();
	}

	public USER findOne(String id) {
		return userRepo.findOne(id);
	}

	public void delete(String id) {
		userRepo.delete(id);
	}

}
