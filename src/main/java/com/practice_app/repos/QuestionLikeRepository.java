package com.practice_app.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice_app.models.QuestionLikeEntity;

public interface QuestionLikeRepository extends JpaRepository<QuestionLikeEntity, Long> {

    Optional<QuestionLikeEntity> findByQuestionIdAndUserId(Long questionId, Long userId);

}