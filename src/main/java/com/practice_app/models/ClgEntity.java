package com.practice_app.models;

import java.util.List;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "colleges_details")
public class ClgEntity {

    @Id
    @Column(name = "clgcode")
    private Integer clgCode;

    @Column(nullable = false)
    private String clgname;

    private String username;
    private String password; 

    private String role = "PRINCIPAL"; 

    // 🔥 One college -> many students
    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    @JsonIgnore  // prevent infinite JSON loop
    private List<StdEntity> students;

    public ClgEntity() {}

    public ClgEntity(Integer clgCode, String clgName, String username, String password) {
        this.clgCode = clgCode;
        this.clgname = clgName;
        this.username = username;
        this.password = password;
        this.role = "PRINCIPAL";
    }

    public Integer getClgCode() {
        return clgCode;
    }

    public void setClgCode(Integer clgCode) {
        this.clgCode = clgCode;
    }

    public String getClgName() {
        return clgname;
    }

    public void setClgName(String clgName) {
        this.clgname = clgName;
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

    public List<StdEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StdEntity> students) {
        this.students = students;
    }
}