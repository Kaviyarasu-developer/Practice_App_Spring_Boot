package com.practice_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice_app.dtos.UserDto;
import com.practice_app.dtos.UserRegisterDto;
import com.practice_app.services.UserService;

@RestController
@RequestMapping("/app/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserRegisterDto dto){
        return userService.registerUser(dto);
    }
}