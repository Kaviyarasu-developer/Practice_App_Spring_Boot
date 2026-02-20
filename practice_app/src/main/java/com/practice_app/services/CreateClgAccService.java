package com.practice_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.ClgAccDto;
import com.practice_app.models.ClgEntity;
import com.practice_app.repos.ClgRepository;

@Service
public class CreateClgAccService {
	
	@Autowired
	private ClgRepository clgrepo;
	public ClgEntity create(ClgAccDto clgAccDto){
		return clgrepo.save(clgAccDto);
	}
	
}
