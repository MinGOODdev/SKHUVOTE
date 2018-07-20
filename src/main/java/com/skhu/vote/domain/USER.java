package com.skhu.vote.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(exclude = "department")
@ToString(exclude = "department")
public class USER {

  @Id
  private String id;
  private String name;
  private int userType;
  private String tel;
  private int confirmCheck;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "departmentId")
  DEPARTMENT department;

}