//package com.practice_app.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.practice_app.dtos.UserResponseDto;
//import com.practice_app.dtos.__UserRegisterDto;
//import com.practice_app.services.__UserService;

//@RestController
//@RequestMapping("/app/users")
//public class UserController {
//
//    @Autowired
//    private __UserService __UserService;
//
//    @PostMapping("/register")
//    public UserResponseDto registerUser(@RequestBody __UserRegisterDto dto){
//        return __UserService.registerUser(dto);
//    }
//}