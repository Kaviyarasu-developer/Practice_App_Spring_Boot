package com.practice_app.dtos;

public class ProfileResponseDto {

    private Long id;
    private String name;
    private String username;
    private String role;

    private Object profile;

    public ProfileResponseDto() {}

    public ProfileResponseDto(Long id, String name, String username, String role, Object profile) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.role = role;
        this.profile = profile;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Object getProfile() {
		return profile;
	}

	public void setProfile(Object profile) {
		this.profile = profile;
	}

}
