package com.skhu.vote.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skhu.vote.domain.CANDIDATE;
import com.skhu.vote.model.CandidateModel;
import com.skhu.vote.repository.CandidateRepository;
import com.skhu.vote.repository.VoteInfoRepository;

@Service
public class CandidateService {

	@Autowired
	CandidateRepository candidateRepo;
	@Autowired
	VoteInfoRepository voteInfoRepo;

	public List<CANDIDATE> findAll() {
		return candidateRepo.findAll();
	}
	
	public CANDIDATE findOne(int candidateId) {
		return candidateRepo.findOne(candidateId);
	}

	public List<CANDIDATE> insertCandidate(CandidateModel c, int voteId) {
		
		List<CANDIDATE> list = new ArrayList<>();
		
		// 후보자 생성
		CANDIDATE candidate = new CANDIDATE();
		candidate.setCampName(c.getCampName());
		candidate.setLeaderName(c.getLeaderName());
		candidate.setLeaderDeptName(c.getLeaderDepName());
		candidate.setSubLeaderName(c.getSubLeaderName());
		candidate.setSubLeaderDeptName(c.getSubLeaderDepName());
		candidate.setPhoto(c.getPhoto());
		candidate.setVoteInfo(voteInfoRepo.findOne(voteId));
		candidateRepo.save(candidate);
		list.add(candidate);
		
		// 기권이 없을 경우만 각 선거의 기권 생성
		if(candidateRepo.findByVoteInfoVoteIdAndCampName(voteId, "기권") == null) {
			System.out.println("아아아");
			CANDIDATE withdraw = new CANDIDATE();
			withdraw.setCampName("기권");
			withdraw.setVoteInfo(voteInfoRepo.findOne(voteId));
			candidateRepo.save(withdraw);
			list.add(withdraw);
		}
		return list;
	}

	public void delete(int candidateId) {
		candidateRepo.delete(candidateId);
	}

}
