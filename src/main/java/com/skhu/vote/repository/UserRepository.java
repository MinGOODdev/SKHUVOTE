package com.skhu.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhu.vote.domain.USER;

public interface UserRepository extends JpaRepository<USER, String> {

	List<USER> findByDepartmentDepartmentId(int target);
	int countByDepartmentDepartmentId(int target);

}
