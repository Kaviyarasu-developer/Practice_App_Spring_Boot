package com.practice_app.services;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.practice_app.dtos.DeleteEventDto;
import com.practice_app.dtos.announcementdtos.AnnouncementResponseDto;
import com.practice_app.models.AnnouncementEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.AnnouncementRepository;
import com.practice_app.repos.UserRepository;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepo;

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // ---------------- CREATE ----------------
    public AnnouncementResponseDto createAnnouncement(
            String title,
            String content,
            Long adminId,
            MultipartFile file
    ) {

        UserEntity admin = userRepo.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!admin.getRole().equals("ADMIN")) {
            throw new RuntimeException("Only admin allowed");
        }

        AnnouncementEntity entity = new AnnouncementEntity();

        entity.setTitle(title);
        entity.setContent(content);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setAdmin(admin);

        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            String uploadDir = System.getProperty("user.dir") + "/uploads/announcements/";

            try {
                File folder = new File(uploadDir);
                if (!folder.exists()) folder.mkdirs();

                File dest = new File(uploadDir + fileName);
                file.transferTo(dest);

                entity.setImageUrl("/uploads/announcements/" + fileName);

            } catch (Exception e) {
                throw new RuntimeException("Image upload failed");
            }
        }

        AnnouncementEntity saved = announcementRepo.save(entity);
        AnnouncementResponseDto dto = convertToDto(saved);

        messagingTemplate.convertAndSend("/topic/announcements", dto);

        return dto;
    }

    // ---------------- GET ALL ----------------
    public List<AnnouncementResponseDto> getAllAnnouncements() {

        return announcementRepo.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    
    @Transactional
    public void deleteAnnouncement(Long id, Long userId) {

        AnnouncementEntity a = announcementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (!a.getAdmin().getId().equals(userId)) {
            throw new RuntimeException("Not allowed");
        }

        if (a.getImageUrl() != null) {

            String path = System.getProperty("user.dir") + a.getImageUrl();

            File file = new File(path);
            if (file.exists()) file.delete();
        }

        announcementRepo.delete(a);
        messagingTemplate.convertAndSend(
                "/topic/announcements/new",
                new DeleteEventDto(id, "DELETE")
            );

    }

    // ---------------- DTO CONVERT ----------------
    private AnnouncementResponseDto convertToDto(AnnouncementEntity e) {

        AnnouncementResponseDto dto = new AnnouncementResponseDto();

        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setContent(e.getContent());
        dto.setCreatedAt(e.getCreatedAt());

        dto.setAdminName(e.getAdmin().getName());

        if (e.getImageUrl() != null) {
            dto.setImageUrl(e.getImageUrl());
        }

        return dto;
    }
}