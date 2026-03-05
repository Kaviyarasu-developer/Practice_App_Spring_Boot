package com.practice_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.StdDto;
import com.practice_app.models.StdEntity;
import com.practice_app.services.StdService;
import com.practice_app.services.UserService;

@RestController
@RequestMapping("/app/students")
public class StdController {
	
	@Autowired
	private StdService stdService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createStd(@RequestBody StdDto stdDto) {
	    StdEntity saved = stdService.createStd(stdDto);
	    userService.createStd(stdDto);

	    return ResponseEntity.ok(saved);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> students(){
		return ResponseEntity.ok(stdService.getAllStudents());
	}
}
