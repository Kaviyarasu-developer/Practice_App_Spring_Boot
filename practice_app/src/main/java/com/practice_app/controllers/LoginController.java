package com.practice_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.practice_app.dtos.LoginDto;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.UserRepository;

public class LoginController {
	
  @PostMapping("/login")	
  public ResponseEntity<?> login(@RequestBody LoginDto request) {
    UserEntity user = UserRepository.findByEmail(request.getEmail());

    if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

    if (!user.getPassword().equals(request.getPassword())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid password");
    }

    return ResponseEntity.ok(user);
  }
}


