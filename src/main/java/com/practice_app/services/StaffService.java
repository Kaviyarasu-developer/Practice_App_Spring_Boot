package com.practice_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.StaffDto;
import com.practice_app.models.ClgEntity;
import com.practice_app.models.StaffEntity;
import com.practice_app.repos.ClgRepository;
import com.practice_app.repos.StaffRepository;

@Service
public class StaffService {
	
	@Autowired
	private StaffRepository staffRepo;
	@Autowired
	private ClgRepository clgRepo;
	
	public StaffEntity createStaff(StaffDto dto) {
		
		ClgEntity college = clgRepo.findById(dto.getClgcode()).orElseThrow(() -> new RuntimeException("College not found"));
		
		StaffEntity entity = new StaffEntity();
		entity.setName(dto.getName());
		entity.setDept(dto.getDept());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setCollege(college);
		return staffRepo.save(entity);
	}
	
	public List<StaffEntity> getAllStaffs() {
		return staffRepo.findAll();
	}
}
