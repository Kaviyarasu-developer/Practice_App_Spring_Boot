package com.practice_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.StaffDto;
import com.practice_app.services.StaffService;
import com.practice_app.services.UserService;

@RestController
@RequestMapping("/app/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public void createStaff(@RequestBody StaffDto dto){		
		staffService.createStaff(dto);
		userService.createStaff(dto);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> staffs(){
		System.out.println(staffService.getAllStaffs());
		return ResponseEntity.ok().body(staffService.getAllStaffs());
	}
}
