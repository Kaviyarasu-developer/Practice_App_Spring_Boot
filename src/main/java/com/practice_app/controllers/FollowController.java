package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.UserDto;
import com.practice_app.services.FollowService;

@RestController
@RequestMapping("/app/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/{followingId}")
    public void follow(
            @PathVariable Long followingId,
            @RequestParam Long followerId) {

        followService.followUser(followerId, followingId);
    }

    @DeleteMapping("/{followingId}")
    public void unfollow(
            @PathVariable Long followingId,
            @RequestParam Long followerId) {

        followService.unfollowUser(followerId, followingId);
    }

    @GetMapping("/followers/{userId}")
    public List<UserDto> followers(@PathVariable Long userId) {
        return followService.getFollowers(userId);
    }

    @GetMapping("/following/{userId}")
    public List<UserDto> following(@PathVariable Long userId) {
        return followService.getFollowing(userId);
    }
    
    @GetMapping("/isFollowing")
    public boolean isFollowing(
            @RequestParam Long followerId,
            @RequestParam Long followingId) {

        return followService.isFollowing(followerId, followingId);
    }
}
