package com.skhu.vote.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ADMIN {
	@Id
    private String id;
	private String name;
	private	String password;
	private String departmentName;
	private String type;			// 일단 엑셀 업로드로 인해 String으로 변경
}
