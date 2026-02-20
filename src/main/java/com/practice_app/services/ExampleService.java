package com.practice_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.models.ExampleEntity;
import com.practice_app.repos.ExampleRepo;

@Service
public class ExampleService {

    @Autowired
    private ExampleRepo repository;

    public ExampleEntity save(ExampleEntity student) {
        return repository.save(student);
    }

    public List<ExampleEntity> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
