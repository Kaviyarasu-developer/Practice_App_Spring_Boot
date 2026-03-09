package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice_app.models.QuestionLikeEntity;

public interface QuestionLikeRepository
extends JpaRepository<QuestionLikeEntity, Long> {

boolean existsByQuestion_IdAndUser_Id(Long questionId, Long userId);

QuestionLikeEntity findByQuestion_IdAndUser_Id(Long questionId, Long userId);

}