package com.practice_app.dtos;

public class UserResponseDto {

    private Long id;
    private String name;
    private String clgname;
    private Long clgcode;
    private String username;
    private String role;
    private String dept;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String name, String username, String role, String clgname, Long clgcode, String dept) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.role = role;
        this.clgname = clgname;
        this.clgcode = clgcode;
        this.dept = dept;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public String getClgname() {
		return clgname;
	}

	public void setClgname(String clgname) {
		this.clgname = clgname;
	}

	public Long getClgcode() {
		return clgcode;
	}

	public void setClgcode(Long clgcode) {
		this.clgcode = clgcode;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
    
}