package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice_app.models.StdEntity;

@Repository
public interface StdRepository extends JpaRepository<StdEntity, Long> {

}