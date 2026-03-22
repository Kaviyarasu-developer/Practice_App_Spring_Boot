package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice_app.models.MentorEntity;

@Repository
public interface MentorRepository extends JpaRepository<MentorEntity, Long> {

}