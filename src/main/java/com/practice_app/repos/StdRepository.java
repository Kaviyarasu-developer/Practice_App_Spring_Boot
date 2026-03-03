package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice_app.models.StdEntity;

public interface StdRepository extends JpaRepository<StdEntity, Integer>{

}
