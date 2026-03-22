package com.practice_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "reply_like_details",
    uniqueConstraints = @UniqueConstraint(columnNames = {"reply_id", "user_id"})
)
public class ReplyLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reply_id", nullable = false)
    private ReplyEntity reply;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    
	public ReplyLikeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReplyLikeEntity(ReplyEntity reply, UserEntity user) {
		super();
		this.reply = reply;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReplyEntity getReply() {
		return reply;
	}

	public void setReply(ReplyEntity reply) {
		this.reply = reply;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}