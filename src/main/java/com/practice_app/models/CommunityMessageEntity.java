package com.practice_app.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "community_messages")
public class CommunityMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long communityId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String imageUrl;

    /// 🔥 CHAT or POST
    private String type; // CHAT / POST

    private LocalDateTime createdAt;
    
	public CommunityMessageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommunityMessageEntity(Long id, Long communityId, UserEntity user, String message, String imageUrl,
			String type, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.communityId = communityId;
		this.user = user;
		this.message = message;
		this.imageUrl = imageUrl;
		this.type = type;
		this.createdAt = createdAt;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}