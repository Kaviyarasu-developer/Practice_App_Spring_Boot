package com.practice_app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "users_details")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
    
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private ClgEntity college;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private StdEntity student;
    
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private MentorEntity mentor;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private StaffEntity staff;
    
    @ManyToMany(mappedBy = "communityMembers")
    @JsonIgnore
    private List<CommunityEntity> communities;
    
    public UserEntity() {}

	public UserEntity(Long id, String name, String username, String password, String role, StdEntity student, MentorEntity mentor, StaffEntity staff, ClgEntity college, List<CommunityEntity> communities) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.student = student;
		this.mentor = mentor;
		this.staff = staff;
		this.college = college;
		this.communities = communities;
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

	public StdEntity getStudent() {
		return student;
	}

	public void setStudent(StdEntity student) {
		this.student = student;
	}
    
	public MentorEntity getMentor() {
		return mentor;
	}
	
	public void setMentor(MentorEntity mentor) {
		this.mentor = mentor;
	}
	
	public StaffEntity getStaff() {
		return staff;
	}
	
	public void setStaff(StaffEntity staff) {
		this.staff = staff;
	}

	public ClgEntity getCollege() {
		return college;
	}

	public void setCollege(ClgEntity college) {
		this.college = college;
	}

	public List<CommunityEntity> getCommunities() {
		return communities;
	}

	public void setCommunities(List<CommunityEntity> communities) {
		this.communities = communities;
	}

}