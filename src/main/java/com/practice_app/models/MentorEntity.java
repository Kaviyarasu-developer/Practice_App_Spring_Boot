package com.practice_app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "mentors_details")
public class MentorEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	private String name;
	private String username;
	private String password;
	private String role = "MENTOR";
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clgcode", nullable = false)
    private ClgEntity college; 
    
	public MentorEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MentorEntity(Long id, String name, String username, String password, String role, ClgEntity college) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = "MENTOR";
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
