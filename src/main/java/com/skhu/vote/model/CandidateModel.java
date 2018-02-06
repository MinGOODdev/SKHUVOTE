package com.skhu.vote.model;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CandidateModel {
	@NotEmpty
	private String campName;

	// For abstention & opposite,  Remark
	// 반대, 기권 등록으로 인한 Not Empty 주석 처리
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
