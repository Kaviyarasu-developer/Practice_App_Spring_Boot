package com.practice_app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice_app.models.ReplyEntity;

public interface ReplyRepository
extends JpaRepository<ReplyEntity, Long> {

List<ReplyEntity> findByQuestion_Id(Long questionId);

}