package com.skhu.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhu.vote.domain.CANDIDATE;

public interface CandidateRepository extends JpaRepository<CANDIDATE, Integer> {

}
