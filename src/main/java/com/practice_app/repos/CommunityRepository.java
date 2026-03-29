package com.practice_app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice_app.models.CommunityEntity;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long>{

    List<CommunityEntity> findByMentor_Id(Long mentorId);
    
    void deleteByCommunityId(Long communityId);

}
