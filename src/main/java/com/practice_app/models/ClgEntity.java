package com.practice_app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "colleges_details")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ClgEntity {

    @Id
    @Column(name = "clgcode")
    private Long clgCode;

    @Column(unique = true, nullable = false)
    private String clgName;
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<StdEntity> students;

    @JsonIgnore
    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MentorEntity> mentors;

    @JsonIgnore
    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<StaffEntity> staffs;

	public ClgEntity() {
		super();
	}

	public ClgEntity(Long clgCode, String clgName, UserEntity user, List<StdEntity> students,
			List<MentorEntity> mentors, List<StaffEntity> staffs) {
		super();
		this.clgCode = clgCode;
		this.clgName = clgName;
		this.user = user;
		this.students = students;
		this.mentors = mentors;
		this.staffs = staffs;
	}

	public Long getClgCode() {
		return clgCode;
	}

	public void setClgCode(Long clgCode) {
		this.clgCode = clgCode;
	}

	public String getClgName() {
		return clgName;
	}

	public void setClgName(String clgName) {
		this.clgName = clgName;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<StdEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StdEntity> students) {
		this.students = students;
	}

	public List<MentorEntity> getMentors() {
		return mentors;
	}

	public void setMentors(List<MentorEntity> mentors) {
		this.mentors = mentors;
	}

	public List<StaffEntity> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<StaffEntity> staffs) {
		this.staffs = staffs;
	}
    
}