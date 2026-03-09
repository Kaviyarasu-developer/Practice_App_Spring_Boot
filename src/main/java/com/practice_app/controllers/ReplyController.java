package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice_app.dtos.ReplyCreateDto;
import com.practice_app.dtos.ReplyDto;
import com.practice_app.services.ReplyService;

@RestController
@RequestMapping("/app/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping
    public ReplyDto addReply(@RequestBody ReplyCreateDto dto) {
        return replyService.addReply(dto);
    }

    @GetMapping("/{questionId}")
    public List<ReplyDto> getReplies(@PathVariable Long questionId) {
        return replyService.getReplies(questionId);
    }
}