package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice_app.models.StaffEntity;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {

}