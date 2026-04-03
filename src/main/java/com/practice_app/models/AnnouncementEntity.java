package com.practice_app.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "announcement_details")
public class AnnouncementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String content;

    private String imageUrl;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private UserEntity admin;

	public AnnouncementEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnnouncementEntity(Long id, String title, String content, String imageUrl, LocalDateTime createdAt,
			UserEntity admin) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.imageUrl = imageUrl;
		this.createdAt = createdAt;
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserEntity getAdmin() {
		return admin;
	}

	public void setAdmin(UserEntity admin) {
		this.admin = admin;
	}

    
}