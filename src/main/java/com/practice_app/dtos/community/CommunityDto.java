package com.practice_app.dtos.community;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityDto {

    private Long communityId;

    private String communityName;

    private String communityDesc;

    private String communityImage;

    private String mentorName;

    private String mentorUsername;
 
    private int membersCount;
    
   @JsonProperty("isJoined")
    private boolean isJoined;

    public CommunityDto() {}

	public CommunityDto(Long communityId, String communityName, String communityDesc, String communityImage,
			String mentorName, String mentorUsername, int membersCount, boolean isJoined) {
		super();
		this.communityId = communityId;
		this.communityName = communityName; 
		this.communityDesc = communityDesc;
		this.communityImage = communityImage;
		this.mentorName = mentorName;
		this.mentorUsername = mentorUsername;
		this.membersCount = membersCount;
		this.isJoined = isJoined;
	}

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCommunityDesc() {
		return communityDesc;
	}

	public void setCommunityDesc(String communityDesc) {
		this.communityDesc = communityDesc;
	}

	public String getCommunityImage() {
		return communityImage;
	}

	public void setCommunityImage(String communityImage) {
		this.communityImage = communityImage;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public String getMentorUsername() {
		return mentorUsername;
	}

	public void setMentorUsername(String mentorUsername) {
		this.mentorUsername = mentorUsername;
	}

	public int getMembersCount() {
		return membersCount;
	}

	public void setMembersCount(int membersCount) {
		this.membersCount = membersCount;
	}

	public boolean isJoined() {
		return isJoined;
	}

	public void setJoined(boolean isJoined) {
		this.isJoined = isJoined;
	}
    
}