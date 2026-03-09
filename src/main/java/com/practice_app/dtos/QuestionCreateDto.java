package com.practice_app.dtos;

public class QuestionCreateDto {

    private String message;
    private Long userId;
    
	public QuestionCreateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionCreateDto(String message, Long userId) {
		super();
		this.message = message;
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
