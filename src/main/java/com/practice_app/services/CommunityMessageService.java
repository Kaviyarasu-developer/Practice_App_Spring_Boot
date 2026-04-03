package com.practice_app.services;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice_app.dtos.DeleteEventDto;
import com.practice_app.dtos.community.CommunityMessageDto;
import com.practice_app.dtos.community.CommunityMessageResponseDto;
import com.practice_app.models.CommunityMessageEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.CommunityMessageRepository;
import com.practice_app.repos.UserRepository;

@Service
@Transactional
public class CommunityMessageService {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private CommunityMessageRepository messageRepo;

    @Autowired
    private UserRepository userRepo;

    public CommunityMessageEntity saveMessage(CommunityMessageDto dto) {

        UserEntity user = userRepo.findById(dto.getUserId()).orElseThrow();
        
        if ("MENTOR".equals(user.getRole()) && (dto.getImageUrl()== null && dto.getMessage() == null)) {
            throw new RuntimeException("Mentor post requires image");
        }

        boolean isPost = dto.getImageUrl() != null && !dto.getImageUrl().isEmpty();
        if (isPost && (dto.getImageUrl() == null || dto.getImageUrl().isEmpty())) {
            throw new RuntimeException("Post must contain image");
        }

        if (!"MENTOR".equals(user.getRole())) {

            long todayCount = messageRepo.countByUserIdAndDate(
                    dto.getUserId(),
                    LocalDate.now()
            );

            if (todayCount >= 3) {
                throw new RuntimeException("Daily limit reached (3)");
            }
        }

        CommunityMessageEntity entity = new CommunityMessageEntity();

        entity.setCommunityId(dto.getCommunityId());
        entity.setUser(user);
        entity.setMessage(dto.getMessage());
        entity.setImageUrl(dto.getImageUrl());
        entity.setType(isPost ? "POST" : "CHAT");
        entity.setCreatedAt(LocalDateTime.now());

        CommunityMessageEntity saved = messageRepo.save(entity);

        CommunityMessageResponseDto responseDto = convertToDto(saved);

        messagingTemplate.convertAndSend(
            "/topic/community/" + saved.getCommunityId(),
            responseDto
        );

        return saved;
    }
    
    public List<CommunityMessageResponseDto> getMessages(Long communityId){
        return messageRepo
                .findByCommunityIdOrderByCreatedAtAsc(communityId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private CommunityMessageResponseDto convertToDto(CommunityMessageEntity m){

        CommunityMessageResponseDto dto = new CommunityMessageResponseDto();

        dto.setId(m.getId());
        dto.setMessage(m.getMessage());
        dto.setImageUrl(m.getImageUrl());
        dto.setCreatedAt(m.getCreatedAt().toString());

        dto.setUserId(m.getUser().getId());
        dto.setUsername(m.getUser().getUsername());
        dto.setRole(m.getUser().getRole());
        dto.setCommunityId(m.getCommunityId());

        return dto;
    }
    
    public void deleteMessage(Long messageId, Long userId) {

        CommunityMessageEntity message = messageRepo
                .findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        if (!message.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can delete only your own message");
        }
        if (message.getImageUrl() != null) {

            String path = System.getProperty("user.dir") + message.getImageUrl();

            File file = new File(path);
            if (file.exists()) file.delete();
        }

        messageRepo.delete(message);
        messagingTemplate.convertAndSend(
        	    "/topic/community/" + message.getCommunityId(),
        	    new DeleteEventDto(messageId, "DELETE")
        	);
    }
}