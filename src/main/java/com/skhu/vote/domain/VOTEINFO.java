package com.skhu.vote.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "candidates")
@EqualsAndHashCode(exclude = "candidates")
public class VOTEINFO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;
    private String voteName;
    private int allCount;
    private int voteCount;
    private Date startTime;
    private Date endTime;
    private int target;

    @OneToMany(mappedBy = "voteInfo")
    List<CANDIDATE> candidates;
}