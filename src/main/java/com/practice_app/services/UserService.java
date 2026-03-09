package com.practice_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.UserDto;
import com.practice_app.dtos.UserRegisterDto;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto registerUser(UserRegisterDto dto){

        UserEntity user = new UserEntity();

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        userRepository.save(user);

        return convertToDto(user);
    }

    private UserDto convertToDto(UserEntity user){

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());

        return dto;
    }
}