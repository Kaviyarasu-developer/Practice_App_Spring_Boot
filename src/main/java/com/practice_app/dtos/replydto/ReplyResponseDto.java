package com.practice_app.dtos.replydto;

import java.time.LocalDateTime;

public class ReplyResponseDto {
	
	private Long userId;
    private Long id;
    private String message;
    private String username;
    private String role;
    private LocalDateTime createdAt;
    private int likesCount;
    private boolean isLiked;

    public ReplyResponseDto() {}

    public ReplyResponseDto(Long id, String message, String username, String role, LocalDateTime createdAt, Long userId) {
        this.id = id;
        this.message = message;
        this.username = username;
        this.role = role;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
}