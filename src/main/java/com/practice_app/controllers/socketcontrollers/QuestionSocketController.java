package com.practice_app.controllers.socketcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.practice_app.dtos.QuestionCreateDto;
import com.practice_app.dtos.replydto.ReplyCreateDto;
import com.practice_app.services.QuestionService;
import com.practice_app.services.ReplyService;

@Controller
public class QuestionSocketController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReplyService replyService;

    // 🔥 CREATE QUESTION
    @MessageMapping("/question.create")
    public void createQuestion(QuestionCreateDto dto) {
        questionService.createQuestion(dto);
    }

    // 🔥 CREATE REPLY
    @MessageMapping("/reply.create")
    public void createReply(ReplyCreateDto dto) {
        replyService.addReply(dto);
    }
}
