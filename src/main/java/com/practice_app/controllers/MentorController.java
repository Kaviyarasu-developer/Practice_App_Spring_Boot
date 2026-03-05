package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.MentorDto;
import com.practice_app.models.MentorEntity;
import com.practice_app.services.MentorService;
import com.practice_app.services.UserService;

@RestController
@RequestMapping("/app/mentor")
public class MentorController {

	@Autowired
	private MentorService mentorService;
	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<?> createMentor(@RequestBody MentorDto Dto) {
		MentorEntity entity = mentorService.create(Dto);
		userService.createMentor(Dto);
		
		return ResponseEntity.ok().body(entity);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<MentorDto>> mentors(){
		return ResponseEntity.ok(mentorService.getAllMentors());
	}

}
