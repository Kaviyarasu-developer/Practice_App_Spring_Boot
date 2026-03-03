package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice_app.models.MentorEntity;

public interface MentorRepository extends JpaRepository<MentorEntity, Integer> {

}
