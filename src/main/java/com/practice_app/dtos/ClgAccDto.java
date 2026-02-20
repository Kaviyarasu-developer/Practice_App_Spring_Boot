package com.practice_app.dtos;

import jakarta.persistence.Id;

public class ClgAccDto {
	private String clgName ;
	private String clgEmail ;
	@Id
	private Integer clgCode ;
	private String clgPassword;
	
	public ClgAccDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClgAccDto(String clgName, String clgEmail, Integer clgCode, String clgPassword) {
		super();
		this.clgName = clgName;
		this.clgEmail = clgEmail;
		this.clgCode = clgCode;
		this.clgPassword = clgPassword;
	}

	public String getClgName() {
		return clgName;
	}

	public void setClgName(String clgName) {
		this.clgName = clgName;
	}

	public String getClgEmail() {
		return clgEmail;
	}

	public void setClgEmail(String clgEmail) {
		this.clgEmail = clgEmail;
	}

	public int getClgCode() {
		return clgCode;
	}

	public void setClgCode(Integer clgCode) {
		this.clgCode = clgCode;
	}

	public String getClgPassword() {
		return clgPassword;
	}

	public void setClgPassword(String clgPassword) {
		this.clgPassword = clgPassword;
	}
	
		
}
