package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.QuestionCreateDto;
import com.practice_app.dtos.QuestionResponseDto;
import com.practice_app.services.QuestionService;

@RestController
@RequestMapping("/app/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /// CREATE QUESTION
    @PostMapping
    public QuestionResponseDto createQuestion(@RequestBody QuestionCreateDto dto) {
        return questionService.createQuestion(dto);
    }

    /// GET ALL QUESTIONS (WITH isLiked)
    @GetMapping
    public List<QuestionResponseDto> getQuestions(@RequestParam Long userId) {
        return questionService.getAllQuestions(userId); 
    }

    /// DELETE QUESTION
    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
    } 

    /// ⭐ LIKE / UNLIKE (TOGGLE)
    @PostMapping("/{questionId}/like")
    public void toggleLike(
            @PathVariable Long questionId,
            @RequestParam Long userId
    ) {
        questionService.toggleLike(questionId, userId);
    }
}