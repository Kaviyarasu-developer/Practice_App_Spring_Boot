package com.practice_app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students_details")
public class StdEntity {

    @Id
    private Long rollno;
    private String name;
    private String dept;
    private String username;
    private String password;
    private String role = "STD";

    // 🔥 Many students -> one college
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clgcode", nullable = false)
    private ClgEntity college;

    public StdEntity() {}

    public StdEntity(Long rollno, String name, String dept,
                     String username, String password, ClgEntity college) {
        this.rollno = rollno;
        this.name = name;
        this.dept = dept;
        this.username = username;
        this.password = password;
        this.college = college;
        this.role = "STD";
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

    public String getDept() {
        return dept;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ClgEntity getCollege() {
        return college;
    }

    public void setCollege(ClgEntity college) {
        this.college = college;
    }

    public String getRole() {
        return role;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}
}