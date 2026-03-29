package com.practice_app.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.practice_app.models.CommunityMessageEntity;

public interface CommunityMessageRepository
        extends JpaRepository<CommunityMessageEntity, Long> {

    List<CommunityMessageEntity> findByCommunityIdOrderByCreatedAtAsc(Long communityId);
    
    @Query("""
    	    SELECT COUNT(m)
    	    FROM CommunityMessageEntity m
    	    WHERE m.user.id = :userId
    	    AND DATE(m.createdAt) = :date
    	""")
    	long countByUserIdAndDate(Long userId, LocalDate date);
}