package com.practice_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.ClgDto;
import com.practice_app.repos.ClgRepository;
import com.practice_app.services.ClgService;
import com.practice_app.services.UserService;

@RestController
@RequestMapping("/app/college")
public class ClgController {
	
	@Autowired
	private ClgService clgService;
	@Autowired
    private UserService userService;
	@Autowired
	private ClgRepository clgRepository;
	
	@PostMapping("/create")
    public void createClg(@RequestBody ClgDto clgDto){
    	clgService.create(clgDto);
    	
    	userService.createClg(clgDto);
    }
    
    @GetMapping("/getall")
    public ResponseEntity<?> colleges(){
    	return ResponseEntity.ok(clgRepository.findAll());
    }
}
