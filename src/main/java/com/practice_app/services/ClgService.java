package com.practice_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.ClgDto;
import com.practice_app.models.ClgEntity;
import com.practice_app.repos.ClgRepository;

@Service
public class ClgService {
	
	@Autowired
	private ClgRepository clgrepo;
	public ClgEntity create(ClgDto Dto){
		
		ClgEntity entity = new ClgEntity();
		entity.setClgName(Dto.getClgName());
		entity.setClgCode(Dto.getClgCode());
		entity.setUsername(Dto.getusername());
		entity.setPassword(Dto.getClgPassword());
		return clgrepo.save(entity);
	}
	
}