package com.practice_app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private CommunityMessageRepository messageRepo;

    @Autowired
    private UserRepository userRepo;

    // ==========================
    // 🔥 SAVE MESSAGE / POST
    // ==========================
    public CommunityMessageEntity saveMessage(CommunityMessageDto dto) {

        UserEntity user = userRepo.findById(dto.getUserId()).orElseThrow();
        
     // mentor post must have image
        if ("MENTOR".equals(user.getRole()) && (dto.getImageUrl()== null && dto.getMessage() == null)) {
            throw new RuntimeException("Mentor post requires image");
        }

        // ==========================
        // 🔥 TYPE DECISION
        // ==========================
        boolean isPost = dto.getImageUrl() != null && !dto.getImageUrl().isEmpty();

        // 🚫 enforce: POST must have image
        if (isPost && (dto.getImageUrl() == null || dto.getImageUrl().isEmpty())) {
            throw new RuntimeException("Post must contain image");
        }

        // ==========================
        // 🔥 STUDENT LIMIT (3/day)
        // ==========================
        if (!"MENTOR".equals(user.getRole())) {

            long todayCount = messageRepo.countByUserIdAndDate(
                    dto.getUserId(),
                    LocalDate.now()
            );

            if (todayCount >= 3) {
                throw new RuntimeException("Daily limit reached (3)");
            }
        }

        // ==========================
        // 🔥 SAVE ENTITY
        // ==========================
        CommunityMessageEntity entity = new CommunityMessageEntity();

        entity.setCommunityId(dto.getCommunityId());
        entity.setUser(user);
        entity.setMessage(dto.getMessage());
        entity.setImageUrl(dto.getImageUrl());
        entity.setType(isPost ? "POST" : "CHAT");
        entity.setCreatedAt(LocalDateTime.now());

        return messageRepo.save(entity);
    }

    // ==========================
    // 🔥 GET HISTORY
    // ==========================
    public List<CommunityMessageResponseDto> getMessages(Long communityId){
        return messageRepo
                .findByCommunityIdOrderByCreatedAtAsc(communityId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    // ==========================
    // 🔥 ENTITY → DTO
    // ==========================
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

        // 🔥 SECURITY CHECK (VERY IMPORTANT)
        if (!message.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can delete only your own message");
        }

        messageRepo.delete(message);
        System.out.println("message deleted successfully");
    }
}