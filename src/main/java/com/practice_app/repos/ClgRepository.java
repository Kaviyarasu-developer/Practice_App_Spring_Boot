package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice_app.models.ClgEntity;

@Repository
public interface ClgRepository extends JpaRepository<ClgEntity, Long> {

    ClgEntity findByUser_Id(Long userId);

}