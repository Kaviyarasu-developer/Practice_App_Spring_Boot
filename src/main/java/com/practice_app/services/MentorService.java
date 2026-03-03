package com.practice_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.MentorDto;
import com.practice_app.models.ClgEntity;
import com.practice_app.models.MentorEntity;
import com.practice_app.repos.ClgRepository;
import com.practice_app.repos.MentorRepository;

@Service
public class MentorService {
	
	@Autowired
	private MentorRepository mentorRepo;
	
	@Autowired
	private ClgRepository clgRepo;
	
	public MentorEntity create(MentorDto Dto) {
		
	    ClgEntity college = clgRepo.findById(Dto.getClgcode())
		        .orElseThrow(() -> new RuntimeException("College not found"));
	    
	    MentorEntity entity = new MentorEntity();
	    entity.setName(Dto.getName());
	    entity.setId(Dto.getId());
	    entity.setUsername(Dto.getUsername());
	    entity.setPassword(Dto.getPassword());
	    
	    entity.setCollege(college);
		
		return mentorRepo.save(entity);
	}
}
