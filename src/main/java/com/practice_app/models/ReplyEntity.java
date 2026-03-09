package com.practice_app.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "reply_details")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public QuestionEntity getQuestion() { return question; }

    public void setQuestion(QuestionEntity question) { this.question = question; }

    public UserEntity getUser() { return user; }

    public void setUser(UserEntity user) { this.user = user; }
}