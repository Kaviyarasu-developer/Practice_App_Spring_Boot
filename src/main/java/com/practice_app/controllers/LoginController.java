package com.practice_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.UserLoginDto;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.UserRepository;


@RestController
@RequestMapping("/app")
public class LoginController {
	
	@Autowired
    private UserRepository UserRepository;  
	
	@Autowired
	private BCryptPasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto request) {

        UserEntity user = UserRepository.findByUsername(request.getUsername());
	 
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

       if (!encoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password");
        }
        
        user.setPassword(null); // Clear the password before sending the response
        return ResponseEntity.ok().body(user);
    } 
}
