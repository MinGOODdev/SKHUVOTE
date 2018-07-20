package com.skhu.vote.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ADMIN {

  @Id
  private String id;
  private String name;
  private String password;
  private String departmentName;
  private String type;
  private Date lastLogin;

}
