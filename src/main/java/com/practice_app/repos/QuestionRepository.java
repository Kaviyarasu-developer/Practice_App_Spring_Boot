package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice_app.models.QuestionEntity;

public interface QuestionRepository
extends JpaRepository<QuestionEntity, Long> {

}