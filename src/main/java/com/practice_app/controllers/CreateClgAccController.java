package com.practice_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.ClgAccDto;
import com.practice_app.services.CreateClgAccService;

@RestController
@RequestMapping
public class CreateClgAccController {
	
	@Autowired
	private CreateClgAccService createClgService;
	public ResponseEntity<?> createClgAcc(@RequestBody ClgAccDto clgAccDto){
		createClgService.create(clgAccDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("clg created");
	}
}
