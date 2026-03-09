package com.practice_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice_app.services.QuestionLikeService;

@RestController
@RequestMapping("/app/questions")
public class QuestionLikeController {

    @Autowired
    private QuestionLikeService likeService;

    @PostMapping("/{questionId}/like")
    public void likeQuestion(
            @PathVariable Long questionId,
            @RequestParam Long userId) {

        likeService.likeQuestion(questionId, userId);
    }

    @DeleteMapping("/{questionId}/like")
    public void unlikeQuestion(
            @PathVariable Long questionId,
            @RequestParam Long userId) {

        likeService.unlikeQuestion(questionId, userId);
    }
}