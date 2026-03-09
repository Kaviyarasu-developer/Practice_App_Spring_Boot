package com.practice_app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
@Entity
@Table(name = "students_details")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class StdEntity {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private Long rollno;
    
    private String year;
    
    @Column(nullable = false)
    private String dept;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "clgcode", nullable = false)
    private ClgEntity college;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private UserEntity user;
    
    public StdEntity() {}

	public StdEntity(Long id, Long rollno, String year, String dept, ClgEntity college, UserEntity user) {
		super();
		this.id = id;
		this.rollno = rollno;
		this.year = year;
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

	public Long getRollno() {
		return rollno;
	}

	public void setRollno(Long rollno) {
		this.rollno = rollno;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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