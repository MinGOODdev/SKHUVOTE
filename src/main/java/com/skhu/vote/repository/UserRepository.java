package com.skhu.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhu.vote.domain.USER;

public interface UserRepository extends JpaRepository<USER, String> {

}
