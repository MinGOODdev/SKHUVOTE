package com.skhu.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhu.vote.domain.VOTEINFO;

public interface VoteInfoRepository extends JpaRepository<VOTEINFO, Integer> {
	
	public List<VOTEINFO> findByVoteId(int voteId);

}
