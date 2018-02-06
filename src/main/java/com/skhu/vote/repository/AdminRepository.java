package com.skhu.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhu.vote.domain.ADMIN;

public interface AdminRepository extends JpaRepository<ADMIN, String> {

}
