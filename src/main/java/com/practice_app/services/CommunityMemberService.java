package com.practice_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice_app.dtos.community.CommunityDto;
import com.practice_app.models.CommunityEntity;
import com.practice_app.models.CommunityMemberEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.CommunityMemberRepository;
import com.practice_app.repos.CommunityRepository;
import com.practice_app.repos.UserRepository;

@Service
@Transactional
public class CommunityMemberService {

    @Autowired
    private CommunityMemberRepository memberRepo;

    @Autowired
    private CommunityRepository communityRepo;

    @Autowired
    private UserRepository userRepo;
    

    
    public boolean isUserJoined(Long communityId, Long userId) {

        return memberRepo
                .existsByCommunity_CommunityIdAndUser_Id(communityId, userId);
    }

    public void toggleJoin(Long communityId, Long userId){

        boolean exists =
            memberRepo.existsByCommunity_CommunityIdAndUser_Id(communityId, userId);

        if(exists){
            // 🔥 LEAVE
            memberRepo.deleteByCommunity_CommunityIdAndUser_Id(communityId, userId);
        } else {
            // 🔥 JOIN
            CommunityEntity community =
                communityRepo.findById(communityId).orElseThrow();

            UserEntity user =
                userRepo.findById(userId).orElseThrow();

            CommunityMemberEntity member = new CommunityMemberEntity();
            member.setCommunity(community);
            member.setUser(user);

            memberRepo.save(member);
        }
    }
    
    public List<CommunityDto> getJoinedCommunities(Long userId){

        return memberRepo.findByUser_Id(userId)
                .stream()
                .map(m -> convertToDto(m.getCommunity(), userId))
                .toList();
    }

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

        // 🔥 MEMBERS COUNT (FROM TABLE)
        dto.setMembersCount(
                memberRepo.countByCommunity_CommunityId(community.getCommunityId())
        );

        // 🔥 IS JOINED (FAST CHECK)
        dto.setJoined(
                memberRepo.existsByCommunity_CommunityIdAndUser_Id(
                        community.getCommunityId(),
                        userId
                )
        );

        return dto;
    }
}
