package com.practice_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.StdDto;
import com.practice_app.models.ClgEntity;
import com.practice_app.models.StdEntity;
import com.practice_app.repos.ClgRepository;
import com.practice_app.repos.StdRepository;

@Service
public class StdService {
	
	@Autowired
	private ClgRepository clgRepository;

	@Autowired
	private StdRepository stdRepository;

	public StdEntity createStd(StdDto dto) {

	    // 1️⃣ fetch college
	    ClgEntity college = clgRepository.findById(dto.getClgcode())
	        .orElseThrow(() -> new RuntimeException("College not found"));

	    // 2️⃣ create student
	    StdEntity student = new StdEntity();
	    student.setRollno(dto.getRollno());
	    student.setName(dto.getName());
	    student.setDept(dto.getDept()); 
	    student.setUsername(dto.getUsername());
	    student.setPassword(dto.getPassword());

	    // 3️⃣ set relationship
	    student.setCollege(college); 

	    // 4️⃣ save
	    return stdRepository.save(student);
	}

}
