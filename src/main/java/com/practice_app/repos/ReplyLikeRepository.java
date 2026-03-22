package com.practice_app.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice_app.models.ReplyLikeEntity;

@Repository
public interface ReplyLikeRepository extends JpaRepository<ReplyLikeEntity, Long> {

    Optional<ReplyLikeEntity> findByReplyIdAndUserId(Long replyId, Long userId);

    int countByReplyId(Long replyId);

    void deleteByReplyIdAndUserId(Long replyId, Long userId);
}
