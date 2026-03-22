package com.practice_app.dtos.community;


public class CommunityCreateDto {

    private String communityName;

    private String communityDesc;

    private String communityImage;
    
    private String communityField;

    private Long mentorId;

    public CommunityCreateDto() {}

	public CommunityCreateDto(String communityName, String communityDesc, String communityImage, Long mentorId, String communityField) {
		super();
		this.communityName = communityName;
		this.communityDesc = communityDesc;
		this.communityImage = communityImage;
		this.mentorId = mentorId;
		this.communityField = communityField;
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

	public Long getMentorId() {
		return mentorId;
	}

	public void setMentorId(Long mentorId) {
		this.mentorId = mentorId;
	}

	public String getCommunityField() {
		return communityField;
	}

	public void setCommunityField(String communityField) {
		this.communityField = communityField;
	}
    
}