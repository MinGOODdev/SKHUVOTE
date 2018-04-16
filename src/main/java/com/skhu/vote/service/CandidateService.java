package com.skhu.vote.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skhu.vote.domain.CANDIDATE;
import com.skhu.vote.model.CandidateModel;
import com.skhu.vote.repository.CandidateRepository;
import com.skhu.vote.repository.VoteInfoRepository;

@Service
public class CandidateService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CandidateRepository candidateRepo;
	@Autowired
	VoteInfoRepository voteInfoRepo;
	@Autowired
	FileUploadService fileUploadService;

	public List<CANDIDATE> findAll() {
		return candidateRepo.findAll();
	}

	public CANDIDATE findOne(int candidateId) {
		return candidateRepo.findOne(candidateId);
	}

	public List<CANDIDATE> insertCandidate(CandidateModel c, int voteId) {

		List<CANDIDATE> list = new ArrayList<>();

		// Candidate Create
		// 후보자 생성
		CANDIDATE candidate = new CANDIDATE();
		candidate.setCampName(c.getCampName());
		candidate.setLeaderName(c.getLeaderName());
		candidate.setLeaderDeptName(c.getLeaderDepName());
		candidate.setSubLeaderName(c.getSubLeaderName());
		candidate.setSubLeaderDeptName(c.getSubLeaderDepName());

//		candidate.setPhoto(fileUploadService.fileUpload(c.getPhoto()));
//		logger.info("imageFile: {}", c.getPhoto());

		candidate.setVoteInfo(voteInfoRepo.findOne(voteId));
		candidateRepo.save(candidate);
		list.add(candidate);

		// Case: Single Candidate -> Opposite Create
		// 단일 후보일 경우, 반대 자동 생성
		if (candidateRepo.findByVoteInfoVoteId(voteId).size() < 2) {
			CANDIDATE opposite = new CANDIDATE();
			opposite.setCampName("반대");
			opposite.setVoteInfo(voteInfoRepo.findOne(voteId));
			candidateRepo.save(opposite);
			list.add(opposite);
		}
		// Continuous Candidate Registration -> Candidate Count > 1 -> Auto Delete Create
		// 연속된 후보자 등록의 경우 반대가 필요없으므로 반대 자동 삭제
		else if (candidateRepo.findByVoteInfoVoteId(voteId).size() > 1) {
			if (candidateRepo.findByVoteInfoVoteIdAndCampName(voteId, "반대") != null) {
				candidateRepo.delete(candidateRepo.findByVoteInfoVoteIdAndCampName(voteId, "반대"));
			}
		}

		// Not Exist Abstention -> Each Election Create Abstention
		// 기권이 존재하지 않는다면, 선거별 기권 자동 생성
		if (candidateRepo.findByVoteInfoVoteIdAndCampName(voteId, "기권") == null) {
			CANDIDATE withdraw = new CANDIDATE();
			withdraw.setCampName("기권");
			withdraw.setVoteInfo(voteInfoRepo.findOne(voteId));
			candidateRepo.save(withdraw);
			list.add(withdraw);
		}
		else {
			candidateRepo.delete(candidateRepo.findByVoteInfoVoteIdAndCampName(voteId, "기권"));
			CANDIDATE withdraw = new CANDIDATE();
			withdraw.setCampName("기권");
			withdraw.setVoteInfo(voteInfoRepo.findOne(voteId));
			candidateRepo.save(withdraw);
			list.add(withdraw);
		}
		return list;
	}

	public void delete(int voteId, int candidateId) {

		candidateRepo.delete(candidateId);

		// 선본이 1팀이었을 경우는 제외. (단일 선본 사퇴했을 경우: 선본 , 반대, 기권을 각각 삭제하면 되므로)
		/*
		 * 선본가 2팀 이상일 경우 선본 1팀 등록 후 DB상 총 팀은 3 (선본1, 반대, 기권) 선본 2팀 등록 후 DB상 총 팀은 3 (선본1,
		 * 선본2, 기권) 선본 3팀 등록 후 DB상 총 팀은 4 (선본1, 선본2, 선본3, 기권) 이 이상은 규칙이 같다.
		 *
		 * 결론적으로 선본을 2팀 등록 후 1팀을 삭제하게 되면 DB상 총 팀은 2 (선본, 기권)가 된다. 그런데 이 경우 남은 선본이 1팀으로
		 * 단일 후보가 된다. 그러므로 반대가 필요하다. 그래서 반대를 자동으로 추가해줄 것이다.
		 */

		/*
		 *	Case 1: Except Only One Candidate -> Resignation -> Candidate, Opposite, Abstention Each Delete
		 *	Case 2: Candidate >= 2 -> Add Candidate -> In DB, Candidate >= 3 (Candidate1, Candidate2, Abstention)
		 *	1. Add Candidate -> In DB, (Candidate1, Opposite, Abstention)
		 *	2. Add Candidate -> In DB, (Candidate1, Candidate2, Abstention)
		 *
		 *	In conclusion, Add 2 candidates -> 1 candidate remove -> In DB, total (candidate1, Abstention)
		 *	By the way, this case remain candidate only one -> need opposite -> so, auto create opposite.
		 */
		if (candidateRepo.findByVoteInfoVoteId(voteId).size() < 3) {
			CANDIDATE opposite = new CANDIDATE();
			opposite.setCampName("반대");
			opposite.setVoteInfo(voteInfoRepo.findOne(voteId));
			candidateRepo.save(opposite);
		}
	}

	public void deleteByVoteId(int voteId) {
		candidateRepo.deleteByVoteId(voteId);
	}

}
