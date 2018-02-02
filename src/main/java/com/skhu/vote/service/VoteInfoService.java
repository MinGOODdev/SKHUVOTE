package com.skhu.vote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skhu.vote.domain.VOTEINFO;
import com.skhu.vote.model.VoteInfoModel;
import com.skhu.vote.repository.VoteInfoRepository;

@Service
public class VoteInfoService {

	@Autowired
	VoteInfoRepository voteInfoRepo;

	public List<VOTEINFO> findAll() {
		return voteInfoRepo.findAll();
	}

	public VOTEINFO findOne(int voteId) {
		return voteInfoRepo.findOne(voteId);
	}

	public VOTEINFO insertVoteInfo(VoteInfoModel v) {
		VOTEINFO voteInfo = new VOTEINFO();
		voteInfo.setVoteName(v.getVoteName());
		voteInfo.setStartTime(v.getStartTime());
		voteInfo.setEndTime(v.getEndTime());
		voteInfo.setTarget(v.getTarget());
		voteInfoRepo.save(voteInfo);
		return voteInfo;
	}

	public void delete(int voteId) {
		voteInfoRepo.delete(voteId);
	}
	
	public double voteRate(int voteId) {
		double allCount = voteInfoRepo.findOne(voteId).getAllCount();
		double voteCount = voteInfoRepo.findOne(voteId).getVoteCount();
		double voteRate = voteCount / allCount * 100;
		return voteRate;
	}
	
}
