package com.skhu.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhu.vote.domain.CANDIDATE;

public interface CandidateRepository extends JpaRepository<CANDIDATE, Integer> {
	
	public CANDIDATE findByVoteInfoVoteIdAndCampName(int voteId, String campName);
	public List<CANDIDATE> findByVoteInfoVoteId(int voteId);
	
}
