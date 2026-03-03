package com.practice_app.dtos;


public class ClgDto {
	private String clgname ;
	private String username ;
	private Integer clgcode ;
	private String clgpassword ;
	//private String role = "STAFF";

	public ClgDto(String clgname, String username, Integer clgcode, String clgpassword, String role) {
		super();
		this.clgname = clgname;
		this.username = username;
		this.clgcode = clgcode;
		this.clgpassword = clgpassword;
		//this.role = "STAFF";
	}
	
	
//	public String getRole() {
//		return role;
//	}
//
//
//	public void setRole(String role) {
//		this.role = role;
//	}


	public ClgDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getClgName() {
		return clgname;
	}

	public void setClgName(String clgName) {
		this.clgname = clgName; 
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public Integer getClgCode() {
		return clgcode;
	}

	public void setClgCode(Integer clgcode) {
		this.clgcode = clgcode;
	}

	public String getClgPassword() {
		return clgpassword;
	}

	public void setClgPassword(String clgpassword) {
		this.clgpassword = clgpassword;
	}
	
		
}
