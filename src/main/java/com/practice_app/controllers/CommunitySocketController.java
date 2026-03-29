package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.community.CommunityMessageDto;
import com.practice_app.dtos.community.CommunityMessageResponseDto;
import com.practice_app.models.CommunityMessageEntity;
import com.practice_app.services.CommunityMessageService;

@RestController
@RequestMapping("/app/community")
public class CommunitySocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private CommunityMessageService messageService;

    @PostMapping("/message")
    public void saveMessage(@RequestBody CommunityMessageDto dto) {
    	System.out.println("message save request recieve from front end");

        CommunityMessageEntity saved = messageService.saveMessage(dto);

        /// 🔥 RESPONSE TO FRONTEND
        messagingTemplate.convertAndSend(
                "/topic/community/" + dto.getCommunityId(),
                saved
        );
    }
    
    @GetMapping("/{communityId}/messages")
    public List<CommunityMessageResponseDto> getMessages(
            @PathVariable Long communityId
    ) {
        return messageService.getMessages(communityId);
    }
}