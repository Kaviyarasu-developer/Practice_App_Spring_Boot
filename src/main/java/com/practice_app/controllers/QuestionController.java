package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice_app.dtos.*;
import com.practice_app.services.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // CREATE QUESTION
    @PostMapping
    public QuestionResponseDto createQuestion(@RequestBody QuestionRequestDto dto) {
        return questionService.createQuestion(dto);
    }

    // GET QUESTIONS
    @GetMapping
    public List<QuestionResponseDto> getQuestions() {
        return questionService.getAllQuestions();
    }

    // LIKE QUESTION
    @PostMapping("/{id}/like")
    public void likeQuestion(@PathVariable Long id, @RequestParam Long userId) {
        questionService.likeQuestion(id, userId);
    }

    // REPLY QUESTION
    @PostMapping("/{id}/reply")
    public void replyQuestion(
            @PathVariable Long id,
            @RequestBody ReplyRequestDto dto) {

        questionService.replyQuestion(id, dto);
    }
    
    // GET REPLIES
    @GetMapping("/{id}/replies")
    public List<ReplyResponseDto> getReplies(@PathVariable Long id){
        return questionService.getReplies(id);
    }
}