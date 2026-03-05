package com.practice_app.models;

import jakarta.persistence.*;

@Entity
@Table(
		 name="question_like_details",
		 uniqueConstraints = @UniqueConstraint(columnNames = {"question_id","user_id"})
		)
public class QuestionLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="question_id")
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

	public QuestionLikeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionLikeEntity(Long id, QuestionEntity question, UserEntity user) {
		super();
		this.id = id;
		this.question = question;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QuestionEntity getQuestion() {
		return question;
	}

	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
    
}
