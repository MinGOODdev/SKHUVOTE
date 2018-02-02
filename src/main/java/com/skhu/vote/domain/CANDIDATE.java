package com.skhu.vote.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "voteInfo")
@EqualsAndHashCode(exclude = "voteInfo")
public class CANDIDATE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;
    private String campName;
    private String leaderName;
    private String leaderDeptName;
    private String subLeaderName;
    private String subLeaderDeptName;
    private String photo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "voteId")
    VOTEINFO voteInfo;
}