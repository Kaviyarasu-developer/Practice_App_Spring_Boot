package com.practice_app.dtos;

import java.time.LocalDateTime;

public class ReplyResponseDto {

    private Long id;
    private String message;
    private String username;
    private String role;
    private LocalDateTime createdAt;

    public ReplyResponseDto() {}

    public ReplyResponseDto(Long id, String message, String username, String role, LocalDateTime createdAt) {
        this.id = id;
        this.message = message;
        this.username = username;
        this.role = role;
        this.createdAt = createdAt;
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
}