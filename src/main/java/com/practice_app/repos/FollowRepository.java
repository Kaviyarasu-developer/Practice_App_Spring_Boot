package com.practice_app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice_app.models.FollowEntity;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Long> {

    boolean existsByFollower_IdAndFollowing_Id(Long followerId, Long followingId);

    List<FollowEntity> findByFollowing_Id(Long followingId);

    List<FollowEntity> findByFollower_Id(Long followerId);

    FollowEntity findByFollower_IdAndFollowing_Id(Long followerId, Long followingId);
    
    long countByFollowing_Id(Long userId);

    long countByFollower_Id(Long userId);
}