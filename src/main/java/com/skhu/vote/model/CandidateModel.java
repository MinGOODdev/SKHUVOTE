package com.skhu.vote.model;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CandidateModel {
	@NotEmpty
	private String campName;
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
