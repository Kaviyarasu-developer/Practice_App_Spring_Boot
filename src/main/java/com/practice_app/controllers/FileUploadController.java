package com.practice_app.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/app")
public class FileUploadController {

    private final String UPLOAD_DIR =
            System.getProperty("user.dir") + "/uploads/";

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {

        try {
            File folder = new File(UPLOAD_DIR);
            if (!folder.exists()) folder.mkdirs();

            String original = file.getOriginalFilename();

            String fileName = System.currentTimeMillis() + "_" +
                    original.replaceAll("\\s+", "_");

            File dest = new File(UPLOAD_DIR + fileName);

            file.transferTo(dest);

            return "/uploads/" + fileName; 

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 DEBUG
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }
}