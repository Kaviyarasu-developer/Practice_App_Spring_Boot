package com.practice_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.community.CommunityCreateDto;
import com.practice_app.dtos.community.CommunityDto;
import com.practice_app.models.CommunityEntity;
import com.practice_app.models.CommunityMemberEntity;
import com.practice_app.models.MentorEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.CommunityMemberRepository;
import com.practice_app.repos.CommunityRepository;
import com.practice_app.repos.MentorRepository;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;
    
    @Autowired
    private CommunityMemberRepository communityMemberRepository;

    @Autowired
    private MentorRepository mentorRepository;

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
    
    // ---------------- GET JOINED COMMUNITIES ----------------

    public List<CommunityDto> getJoinedCommunities(Long userId){

        return communityMemberRepository.findByUser_Id(userId)
                .stream()
                .map(m -> convertToDto(m.getCommunity(), userId))
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


//    // ---------------- JOIN COMMUNITY ----------------
//
//    public void joinCommunity(Long communityId, Long userId){
//
//        CommunityEntity community =
//                communityRepository.findById(communityId).orElseThrow();
//
//        UserEntity user =
//                userRepository.findById(userId).orElseThrow();
//
//        if (!community.getCommunityMembers().contains(user)) {
//            community.getCommunityMembers().add(user);
//        }
//        
//        communityRepository.save(community);
//    }
//
//
//    // ---------------- LEAVE COMMUNITY ----------------
//
//    public void leaveCommunity(Long communityId, Long userId){
//
//        CommunityEntity community =
//                communityRepository.findById(communityId).orElseThrow();
//
//        UserEntity user =
//                userRepository.findById(userId).orElseThrow();
//
//        community.getCommunityMembers().remove(user);
//
//        communityRepository.save(community);
//    }

 
    // ---------------- GET COMMUNITY MEMBERS ----------------

    public List<UserEntity> getCommunityMembers(Long communityId){

        return communityMemberRepository.findByCommunity_CommunityId(communityId)
                .stream()
                .map(CommunityMemberEntity::getUser)
                .toList();
    }

    // ---------------- REMOVE COMMUNITY MEMBER ----------------

    public void removeCommunityMember(Long communityId, Long userId){

        communityMemberRepository.deleteByCommunity_CommunityIdAndUser_Id(communityId, userId);
    }


    // ---------------- DELETE COMMUNITY ----------------
    public void deleteCommunity(Long communityId){

        // 🔥 First delete all members
        communityMemberRepository.deleteAllByCommunity_CommunityId(communityId);

        // 🔥 Then delete community
        communityRepository.deleteById(communityId);
    }

    // ---------------- CONVERT ENTITY → DTO ----------------
    private CommunityDto convertToDto(CommunityEntity community, Long userId){

        CommunityDto dto = new CommunityDto();

        dto.setCommunityId(community.getCommunityId());      
        dto.setMentorId(community.getMentor().getId());
        dto.setCommunityName(community.getCommunityName());
        dto.setCommunityDesc(community.getCommunityDesc());
        dto.setCommunityImage(community.getCommunityImage());

        dto.setMentorName(
                community.getMentor().getUser().getName()
        );

        dto.setMentorUsername(
                community.getMentor().getUser().getUsername()
        );

        // 🔥 MEMBERS COUNT (FAST)
        dto.setMembersCount(
                communityMemberRepository.countByCommunity_CommunityId(community.getCommunityId())
        );

        // 🔥 IS JOINED (FAST)
        dto.setJoined(
                communityMemberRepository.existsByCommunity_CommunityIdAndUser_Id(
                        community.getCommunityId(),
                        userId
                )
        );

        return dto;
    }

}
