package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.practice_app.dtos.announcementdtos.AnnouncementResponseDto;
import com.practice_app.services.AnnouncementService;

@RestController
@RequestMapping("/app/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService service;

    @PostMapping("/create")
    public AnnouncementResponseDto createAnnouncement(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam Long adminId,
            @RequestParam(required = false) MultipartFile file
    ) {
        return service.createAnnouncement(title, content, adminId, file);
    }

    @GetMapping
    public List<AnnouncementResponseDto> getAll() {
        return service.getAllAnnouncements();
    }
    
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            @RequestParam Long userId
    ) {
        service.deleteAnnouncement(id, userId);
    }
}