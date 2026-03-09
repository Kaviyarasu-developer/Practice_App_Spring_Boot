package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.QuestionCreateDto;
import com.practice_app.dtos.QuestionDto;
import com.practice_app.services.QuestionService;

@RestController
@RequestMapping("/app/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public QuestionDto createQuestion(@RequestBody QuestionCreateDto dto) {
        return questionService.createQuestion(dto);
    }

    @GetMapping
    public List<QuestionDto> getQuestions() {
        return questionService.getAllQuestions();
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}