package com.practice_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.ClgDto;
import com.practice_app.dtos.MentorDto;
import com.practice_app.dtos.StaffDto;
import com.practice_app.dtos.StdDto;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.UserRepository;

@Service
public class UserService {	
	@Autowired
	UserRepository userRepository;

	public void createClg(ClgDto Dto) {		
		UserEntity entity = new UserEntity();
		entity.setUsername(Dto.getusername());
		entity.setPassword(Dto.getClgPassword());
		entity.setRole("PRINCIPAL");
		userRepository.save(entity);
	}
	
	public void createStd(StdDto Dto) {
		UserEntity entity = new UserEntity();
		entity.setUsername(Dto.getUsername());
		entity.setPassword(Dto.getPassword());	
		entity.setRole("STD");
		userRepository.save(entity);		
	}
	
	public void createMentor(MentorDto Dto) {
		UserEntity entity = new UserEntity();
		entity.setUsername(Dto.getUsername());
		entity.setPassword(Dto.getPassword());
		entity.setRole("MENTOR");
		userRepository.save(entity);		
	}
	
	public void createStaff(StaffDto Dto) {
		UserEntity entity = new UserEntity();
		entity.setUsername(Dto.getUsername());
		entity.setPassword(Dto.getPassword());
		entity.setRole("STAFF");
		userRepository.save(entity);
	}
	
}
