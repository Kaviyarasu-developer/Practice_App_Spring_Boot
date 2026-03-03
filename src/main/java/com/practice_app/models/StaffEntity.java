package com.practice_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff_details")
public class StaffEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String dept;
	private String username;
	private String password;
	private String role = "STAFF";
	
    // 🔥 Many students -> one college
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clgcode", nullable = false)
    private ClgEntity college;

	public StaffEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StaffEntity(Long id, String name, String dept, String username, String password, String role,
			ClgEntity college) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.username = username;
		this.password = password;
		this.role = role;
		this.college = college;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ClgEntity getCollege() {
		return college;
	}

	public void setCollege(ClgEntity college) {
		this.college = college;
	}
    
}
