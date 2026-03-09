package com.practice_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.UserRegisterDto;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.UserRepository;


@RestController
@RequestMapping("/app")
public class LoginController {
	
	@Autowired
    private UserRepository userRepository;   

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRegisterDto request) {

        UserEntity user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password");
        }

        return ResponseEntity.ok().body(user);
    } 
}


