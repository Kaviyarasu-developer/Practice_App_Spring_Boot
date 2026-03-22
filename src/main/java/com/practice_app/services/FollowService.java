package com.practice_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.UserResponseDto;
import com.practice_app.models.FollowEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.FollowRepository;
import com.practice_app.repos.UserRepository;

@Service
public class FollowService { 
	
	@Autowired
	private FollowRepository followRepository;
	
	@Autowired
	private UserRepository UserRepository;

	public void followUser(Long followerId, Long followingId) {

	    if (followRepository.existsByFollower_IdAndFollowing_Id(followerId, followingId)) {
	        return;
	    }

	    UserEntity follower = UserRepository.findById(followerId).orElseThrow();
	    UserEntity following = UserRepository.findById(followingId).orElseThrow();

	    FollowEntity follow = new FollowEntity();
	    follow.setFollower(follower);
	    follow.setFollowing(following);

	    followRepository.save(follow);
	}
	
	
	public void unfollowUser(Long followerId, Long followingId) {

	    FollowEntity follow =
	            followRepository.findByFollower_IdAndFollowing_Id(followerId, followingId);

	    if (follow != null) {
	        followRepository.delete(follow);
	    }
	}


	public List<UserResponseDto> getFollowers(Long userId) {

	    List<FollowEntity> follows =
	            followRepository.findByFollowing_Id(userId);

	    return follows.stream()
	            .map(f -> convertToDto(f.getFollower()))
	            .toList();
	}
	
	public List<UserResponseDto> getFollowing(Long userId) {

	    List<FollowEntity> follows =
	            followRepository.findByFollower_Id(userId);

	    return follows.stream()
	            .map(f -> convertToDto(f.getFollowing()))
	            .toList();
	}
	
	private UserResponseDto convertToDto(UserEntity user) {

	    if (user == null) {
	        return null;
	    }

	    UserResponseDto dto = new UserResponseDto();

	    dto.setId(user.getId());
	    dto.setName(user.getName());
	    dto.setUsername(user.getUsername());
	    dto.setRole(user.getRole());

	    return dto;
	}
	
	public long getFollowersCount(Long userId){
	    return followRepository.countByFollowing_Id(userId);
	}

	public long getFollowingCount(Long userId){
	    return followRepository.countByFollower_Id(userId);
	}
	
	public boolean isFollowing(Long followerId, Long followingId) {

	    return followRepository
	            .existsByFollower_IdAndFollowing_Id(followerId, followingId);
	}
	
}
