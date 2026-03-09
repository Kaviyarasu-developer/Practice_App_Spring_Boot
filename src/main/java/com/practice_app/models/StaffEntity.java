package com.practice_app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "staff_details")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class StaffEntity {
	
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String dept;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clgcode", nullable = false)
    private ClgEntity college;
    
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private UserEntity user;

	public StaffEntity() {
		super();
	}

	public StaffEntity(Long id, String dept, ClgEntity college, UserEntity user) {
		super();
		this.id = id;
		this.dept = dept;
		this.college = college;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public ClgEntity getCollege() {
		return college;
	}

	public void setCollege(ClgEntity college) {
		this.college = college;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
