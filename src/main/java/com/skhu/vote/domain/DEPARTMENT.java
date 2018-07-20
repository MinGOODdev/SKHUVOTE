package com.skhu.vote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = "userList")
@ToString(exclude = "userList")
public class DEPARTMENT {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int departmentId;

  private String departmentName;

  @JsonIgnore
  @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
  List<USER> userList;

}