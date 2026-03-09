package com.practice_app.dtos;

public class QuestionDto {

    private Long id;
    private String message;
    private int likesCount;
    private int replyCount;
    private String username;
    
    public QuestionDto() {
	}

	public QuestionDto(Long id, String message, int likesCount, int replyCount, String username) {
		super();
		this.id = id;
		this.message = message;
		this.likesCount = likesCount;
		this.replyCount = replyCount;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}    

}
