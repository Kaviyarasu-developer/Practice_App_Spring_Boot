package com.practice_app.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    // 🔥 ADD THIS (VERY IMPORTANT)
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionLikeEntity> likes;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReplyEntity> replies;
    
	public QuestionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionEntity(Long id, String message, int likesCount, int replyCount, LocalDateTime createdAt,
			UserEntity user, List<QuestionLikeEntity> likes, List<ReplyEntity> replies) {
		super();
		this.id = id;
		this.message = message;
		this.likesCount = likesCount;
		this.replyCount = replyCount;
		this.createdAt = createdAt;
		this.user = user;
		this.likes = likes;
		this.replies = replies;
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

	public List<QuestionLikeEntity> getLikes() {
		return likes;
	}

	public void setLikes(List<QuestionLikeEntity> likes) {
		this.likes = likes;
	}

	public List<ReplyEntity> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyEntity> replies) {
		this.replies = replies;
	}
    
}
