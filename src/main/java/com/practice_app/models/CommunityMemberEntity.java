package com.practice_app.models;

import java.time.LocalDateTime;

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
    name = "community_members",
    uniqueConstraints = @UniqueConstraint(columnNames = {"community_id", "user_id"})
)
public class CommunityMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private CommunityEntity community;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private LocalDateTime joinedAt = LocalDateTime.now();

	public CommunityMemberEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

    public CommunityMemberEntity(Long id, CommunityEntity community, UserEntity user, LocalDateTime joinedAt) {
		super();
		this.id = id;
		this.community = community;
		this.user = user;
		this.joinedAt = joinedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CommunityEntity getCommunity() {
		return community;
	}

	public void setCommunity(CommunityEntity community) {
		this.community = community;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}
    
}
