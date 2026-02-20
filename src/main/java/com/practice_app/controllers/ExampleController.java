package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.models.ExampleEntity;
import com.practice_app.services.ExampleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/students")
public class ExampleController {

    @Autowired
    private ExampleService service;

    @PostMapping
    public ExampleEntity create(@RequestBody ExampleEntity student) {
        return service.save(student);
    }

    @GetMapping
    public List<ExampleEntity> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
