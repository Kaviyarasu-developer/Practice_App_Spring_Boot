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

import com.practice_app.dtos.replydto.ReplyCreateDto;
import com.practice_app.dtos.replydto.ReplyResponseDto;
import com.practice_app.services.ReplyService;

@RestController
@RequestMapping("/app/replies")
public class ReplyController {

    @Autowired 
    private ReplyService replyService;

    @PostMapping
    public ReplyResponseDto addReply(@RequestBody ReplyCreateDto dto) {
        return replyService.addReply(dto);
    }

    @GetMapping("/{questionId}")
    public List<ReplyResponseDto> getReplies(
        @PathVariable Long questionId,
        @RequestParam Long userId
    ){
        return replyService.getReplies(questionId, userId);
    }
    
    @DeleteMapping("/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
    	replyService.deleteReply(replyId);
    }
    
    @PostMapping("/{replyId}/like")
    public void likeReply(
        @PathVariable Long replyId,
        @RequestParam Long userId
    ){
        replyService.toggleReplyLike(replyId, userId);
    }
}