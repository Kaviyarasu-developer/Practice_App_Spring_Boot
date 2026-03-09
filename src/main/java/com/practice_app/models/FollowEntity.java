package com.practice_app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "follow_details",
uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id","following_id"}))
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // person who follows
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private UserEntity follower;

    // person being followed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private UserEntity following;

	public FollowEntity() {
		super();
	}

	public FollowEntity(Long id, UserEntity follower, UserEntity following) {
		super();
		this.id = id;
		this.follower = follower;
		this.following = following;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getFollower() {
		return follower;
	}

	public void setFollower(UserEntity follower) {
		this.follower = follower;
	}

	public UserEntity getFollowing() {
		return following;
	}

	public void setFollowing(UserEntity following) {
		this.following = following;
	}

}
