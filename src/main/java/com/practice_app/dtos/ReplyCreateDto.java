package com.practice_app.dtos;

public class ReplyCreateDto {

    private String message;
    private Long questionId;
    private Long userId;

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Long getQuestionId() { return questionId; }

    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}