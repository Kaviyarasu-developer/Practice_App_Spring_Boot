package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice_app.models.StaffEntity;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {

}
