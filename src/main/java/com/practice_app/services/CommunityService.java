package com.practice_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.community.CommunityCreateDto;
import com.practice_app.dtos.community.CommunityDto;
import com.practice_app.models.CommunityEntity;
import com.practice_app.models.MentorEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.CommunityRepository;
import com.practice_app.repos.MentorRepository;
import com.practice_app.repos.UserRepository;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private MentorRepository mentorRepository;
    
    @Autowired
    private UserRepository userRepository;

    // ---------------- CREATE COMMUNITY ----------------

    public void createCommunity(CommunityCreateDto dto){

        MentorEntity mentor =
                mentorRepository.findById(dto.getMentorId()).orElseThrow();

        CommunityEntity community = new CommunityEntity();

        community.setCommunityName(dto.getCommunityName());
        community.setCommunityDesc(dto.getCommunityDesc());
        community.setCommunityImage(dto.getCommunityImage());
        community.setCommunityField(dto.getCommunityField());
        community.setMentor(mentor);

        communityRepository.save(community);
    }


    // ---------------- GET ALL COMMUNITIES ----------------

    public List<CommunityDto> getAllCommunities(Long userId){

        return communityRepository.findAll()
                .stream()
                .map(community -> convertToDto(community, userId))
                .toList(); 
    }
     
    // ---------------- GET ALL COMMUNITY BY MENTOR ID ---------------- 

    public List<CommunityDto> getAllCommunitybyMentorId(Long mentorId, Long userId){

        return communityRepository.findByMentor_Id(mentorId)
				.stream()
				.map(community -> convertToDto(community, userId))
				.toList();
    }

    // ---------------- GET COMMUNITY BY COMMUNITY ID ----------------

    public CommunityDto getCommunityById(Long communityId, Long userId){

        CommunityEntity community =
                communityRepository.findById(communityId).orElseThrow();

        return convertToDto(community, userId);
    }


    // ---------------- JOIN COMMUNITY ----------------

    public void joinCommunity(Long communityId, Long userId){

        CommunityEntity community =
                communityRepository.findById(communityId).orElseThrow();

        UserEntity user =
                userRepository.findById(userId).orElseThrow();

        if (!community.getCommunityMembers().contains(user)) {
            community.getCommunityMembers().add(user);
        }
        
        communityRepository.save(community);
    }


    // ---------------- LEAVE COMMUNITY ----------------

    public void leaveCommunity(Long communityId, Long userId){

        CommunityEntity community =
                communityRepository.findById(communityId).orElseThrow();

        UserEntity user =
                userRepository.findById(userId).orElseThrow();

        community.getCommunityMembers().remove(user);

        communityRepository.save(community);
    }

 
    // ---------------- GET COMMUNITY MEMBERS ----------------

    public List<UserEntity> getCommunityMembers(Long communityId){

        CommunityEntity community =
                communityRepository.findById(communityId).orElseThrow();

        return community.getCommunityMembers();
    }

    // ---------------- REMOVE COMMUNITY MEMBER ----------------

    public void removeCommunityMember(Long communityId, Long userId){

        CommunityEntity community =
                communityRepository.findById(communityId).orElseThrow();

        UserEntity user =
                userRepository.findById(userId).orElseThrow();

        community.getCommunityMembers().remove(user);

        communityRepository.save(community);
    }


    // ---------------- DELETE COMMUNITY ----------------
    public void deleteCommunity(Long communityId){

        communityRepository.deleteById(communityId);
    }

    // ---------------- CONVERT ENTITY → DTO ----------------
    private CommunityDto convertToDto(CommunityEntity community, Long userId){

        CommunityDto dto = new CommunityDto();

        dto.setCommunityId(community.getCommunityId());
        dto.setCommunityName(community.getCommunityName());
        dto.setCommunityDesc(community.getCommunityDesc());
        dto.setCommunityImage(community.getCommunityImage());
        

        dto.setMentorName(
                community.getMentor().getUser().getName()
        );
        
        dto.setMentorUsername(
                community.getMentor().getUser().getUsername()
        );

        dto.setMembersCount(
                community.getCommunityMembers() == null
                        ? 0
                        : community.getCommunityMembers().size()
        );
        
        List<UserEntity> members = community.getCommunityMembers();

        boolean isJoined = members != null &&
                members.stream()
                       .anyMatch(member -> member.getId().equals(userId));
        //System.out.println(isJoined);
        
        dto.setJoined(isJoined);

        return dto;
    }

}
