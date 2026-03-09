package com.practice_app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "mentors_details")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class MentorEntity {
	
	@Id
	private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "clgcode", nullable = true)
    private ClgEntity college; 
    
    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;
    
    private String expertise;
    
    private String specialization;
    
	public MentorEntity() {
		super();
	}

	public MentorEntity(Long id, ClgEntity college, UserEntity user, String expertise, String specialization) {
		super();
		this.id = id;
		this.college = college;
		this.user = user;
		this.expertise = expertise;
		this.specialization = specialization;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
    
}
