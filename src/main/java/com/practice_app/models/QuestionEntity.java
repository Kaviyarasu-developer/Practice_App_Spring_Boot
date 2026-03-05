package com.practice_app.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity	
@Table(name = "question_details")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private int likesCount;

    private int replyCount;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

	public QuestionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionEntity(Long id, String message, int likesCount, int replyCount, LocalDateTime createdAt,
			UserEntity user) {
		super();
		this.id = id;
		this.message = message;
		this.likesCount = likesCount;
		this.replyCount = replyCount;
		this.createdAt = createdAt;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
    
}
