package com.practice_app.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import com.practice_app.models.ExampleEntity;

public interface ExampleRepo extends JpaRepository<ExampleEntity, Long> {
	
}

