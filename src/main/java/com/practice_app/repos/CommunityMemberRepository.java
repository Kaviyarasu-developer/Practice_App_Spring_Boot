package com.practice_app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice_app.models.CommunityMemberEntity;

@Repository
public interface CommunityMemberRepository
        extends JpaRepository<CommunityMemberEntity, Long> {

    // ✔ check joined
    boolean existsByCommunity_CommunityIdAndUser_Id(Long communityId, Long userId);

    // ✔ remove single member
    void deleteByCommunity_CommunityIdAndUser_Id(Long communityId, Long userId);

    // ✔ get all communities of user
    List<CommunityMemberEntity> findByUser_Id(Long userId);

    // ✔ count members
    int countByCommunity_CommunityId(Long communityId);

    // ✔ get all members of a community
    List<CommunityMemberEntity> findByCommunity_CommunityId(Long communityId);

    // ✔ delete all members of a community
    void deleteAllByCommunity_CommunityId(Long communityId);
}
