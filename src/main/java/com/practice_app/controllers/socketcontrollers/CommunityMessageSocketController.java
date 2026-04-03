package com.practice_app.controllers.socketcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.practice_app.dtos.community.CommunityMessageDto;
import com.practice_app.services.CommunityMessageService;

@Controller
public class CommunityMessageSocketController {

    @Autowired
    private CommunityMessageService service;

    @MessageMapping("/community.send")
    public void sendMessage(CommunityMessageDto dto) {
        service.saveMessage(dto);
    }
}