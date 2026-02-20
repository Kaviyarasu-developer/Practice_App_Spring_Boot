package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice_app.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    static UserEntity findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}

