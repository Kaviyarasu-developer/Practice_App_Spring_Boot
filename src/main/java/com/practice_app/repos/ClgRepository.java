package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice_app.dtos.ClgAccDto;
import com.practice_app.models.ClgEntity;

public interface ClgRepository extends JpaRepository<ClgEntity,Integer>{

	ClgEntity save(ClgAccDto clgAccDto);
}
