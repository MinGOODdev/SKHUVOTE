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

	public VOTEINFO findOne(int id) {
		return voteInfoRepo.findOne(id);
	}

	public VOTEINFO insertVoteInfo(VoteInfoModel v) {
		VOTEINFO voteInfo = new VOTEINFO();
		voteInfo.setVoteName(v.getVoteName());
		voteInfo.setStartTime(v.getStartTime());
		voteInfo.setEndTime(v.getEndTime());

		voteInfo.setTarget(v.getTarget());
		// target이 투표 대상(학과)라면 department를 모두 넣어야하는 것 아닌가??
		// 선거 등록할 때 학과 목록 중 선택해야하니까??

		voteInfoRepo.save(voteInfo);
		return voteInfo;
	}

	public void delete(int id) {
		voteInfoRepo.delete(id);
	}
}
