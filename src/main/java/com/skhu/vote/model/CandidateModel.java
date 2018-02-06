package com.skhu.vote.model;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CandidateModel {
	@NotEmpty
	private String campName;

	// 기권, 반대 등록을 위해 나머지 NotEmpty 주석 처리
//	@NotEmpty
	private String leaderName;
//	@NotEmpty
	private String leaderDepName;
//	@NotEmpty
	private String subLeaderName;
//	@NotEmpty
	private String subLeaderDepName;
//	@NotEmpty
	private String photo;
}
