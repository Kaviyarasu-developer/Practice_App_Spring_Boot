package com.practice_app.dtos;

public class StdDto {

	private Long rollno;
	private String name;
	private String dept;
	private String username;
	private String password;
	private Integer clgcode;

        
	public StdDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StdDto(Long rollno, String name, String dept, String username, String password, Integer clgcode) {
		super();
		this.rollno = rollno;
		this.name = name;
		this.dept = dept;
		this.username = username;
		this.password = password;
        this.clgcode = clgcode;
	}

	public Long getRollno() {
		return rollno;
	}

	public void setRollno(Long rollno) {
		this.rollno = rollno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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

    public Integer getClgcode() {
        return clgcode;
    }

    public void setClgcode(Integer clgcode) {
        this.clgcode = clgcode;
    }
	
}
