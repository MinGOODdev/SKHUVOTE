package com.skhu.vote.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class VoteInfoModel {
	@NotEmpty
	private int voteId;
	@NotEmpty
    private String voteName;
	@NotEmpty
    private Date startTime;
	@NotEmpty
    private Date endTime;
	@NotEmpty
    private int target;
}
