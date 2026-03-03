package com.practice_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice_app.dtos.UserDto;
import com.practice_app.models.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

	void save(UserDto userDto);
}

