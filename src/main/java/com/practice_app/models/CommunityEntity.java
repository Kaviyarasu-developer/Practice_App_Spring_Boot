package com.practice_app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "communities_details")
public class CommunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "community_id")
    private Long communityId;

    @Column(nullable = false)
    private String communityName;

    private String communityDesc;

    private String communityImage;
    
    private String communityField;

    /// COMMUNITY CREATOR (MENTOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private MentorEntity mentor;

    /// COMMUNITY MEMBERS
    @ManyToMany
    @JoinTable(
        name = "community_members",
        joinColumns = @JoinColumn(name = "community_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    ) 
    @JsonIgnore
    private List<UserEntity> communityMembers;

	public CommunityEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    	public CommunityEntity(Long communityId, String communityName, String communityDesc, String communityImage,
			MentorEntity mentor, List<UserEntity> communityMembers, String communityfield) {
		super();
		this.communityId = communityId;
		this.communityName = communityName;
		this.communityDesc = communityDesc;
		this.communityImage = communityImage;
		this.mentor = mentor;
		this.communityMembers = communityMembers;
		this.communityField = communityfield;
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

		public MentorEntity getMentor() {
			return mentor;
		}

		public void setMentor(MentorEntity mentor) {
			this.mentor = mentor;
		}

		public List<UserEntity> getCommunityMembers() {
			return communityMembers;
		}

		public void setCommunityMembers(List<UserEntity> communityMembers) {
			this.communityMembers = communityMembers;
		}

		public String getCommunityField() {
			return communityField;
		}

		public void setCommunityField(String communityField) {
			this.communityField = communityField;
		}

}