package com.skhu.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhu.vote.domain.DEPARTMENT;

public interface DepartmentRepository extends JpaRepository<DEPARTMENT, Integer> {

}
