package com.practice_app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice_app.models.AnnouncementEntity;

public interface AnnouncementRepository
extends JpaRepository<AnnouncementEntity, Long> {

List<AnnouncementEntity> findAllByOrderByCreatedAtDesc();
}