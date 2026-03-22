package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.AccountCreateDto;
import com.practice_app.dtos.AccountResponseDto;
import com.practice_app.dtos.UserResponseDto;
import com.practice_app.services.AccountService;

@RestController
@RequestMapping("/app/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public void createAccount(@RequestBody AccountCreateDto dto){

        accountService.createAccount(dto);

    }
    
    @GetMapping("/{role}/getall")
    public List<UserResponseDto> getAllAccountsByRole(@PathVariable String role){

		return accountService.getAllAccountsByRole(role);

	}
    
    @GetMapping("/{userId}")
    public AccountResponseDto getAccount(@PathVariable Long userId) {
        return accountService.getAccount(userId);
    }
}