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
	private	String password;		// DB VARCHAR(255) change
	private String departmentName;
	private String type;			// change
	private Date lastLogin;
}
