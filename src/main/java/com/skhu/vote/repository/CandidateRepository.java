package com.skhu.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhu.vote.domain.CANDIDATE;

public interface CandidateRepository extends JpaRepository<CANDIDATE, Integer> {
	
	CANDIDATE findByVoteInfoVoteIdAndCampName(int voteId, String campName);
	List<CANDIDATE> findByVoteInfoVoteId(int voteId);
	void deleteByVoteId(int voteId);
	
}
