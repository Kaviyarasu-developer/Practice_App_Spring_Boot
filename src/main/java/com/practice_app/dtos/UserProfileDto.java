package com.practice_app.dtos;

public class UserProfileDto {

    private Long id;
    private String name;
    private String username;
    private String role;

    private long followers;
    private long following;

    public UserProfileDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role=role;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers=followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following=following;
    }
}