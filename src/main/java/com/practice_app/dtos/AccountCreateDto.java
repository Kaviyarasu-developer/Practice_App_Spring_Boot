package com.practice_app.dtos;

public class AccountCreateDto {

    private String name;
    private String username;
    private String password;
    private String role;

    private Long rollno;
    private String dept;
    private Long clgcode;
    private String clgname;

	public AccountCreateDto() {
	}
	
	public AccountCreateDto(String name, String username, String password, String role, Long rollno, String dept,
			Long clgcode, String clgname) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.rollno = rollno;
		this.dept = dept;
		this.clgcode = clgcode;
		this.clgname = clgname;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
	
	public String getRole() {
		return role;
	}	
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public Long getRollno() {
		return rollno;
	}
	
	public void setRollno(Long rollno) {
		this.rollno = rollno;
	}
	
	public String getDept() {
		return dept;
	}
	
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public Long getClgcode() {
		return clgcode;
	}
	
	public void setClgcode(Long clgcode) {
		this.clgcode = clgcode;
	}
	
	public String getClgname() {
		return clgname;
	}
	
	public void setClgname(String clgname) {
		this.clgname = clgname;
	}
	
}
