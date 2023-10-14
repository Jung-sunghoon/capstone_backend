package com.example.demo.controller;

import com.example.demo.dao.ImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ImageTestController {

    @Autowired
    private ImageDAO imageDAO;

    @PostMapping("/upload_image_test")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                Path dirPath = Paths.get("src", "main", "uploaded_files");
                if (Files.notExists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                Path filePath = dirPath.resolve(imageFile.getOriginalFilename());
                Files.write(filePath, bytes);

                String imagePath = filePath.toString();
                //imageDAO.saveImage(imagePath);
                return ResponseEntity.ok(imagePath);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("이미지 저장 중 오류 발생");
            }
        } else {
            return ResponseEntity.badRequest().body("제공된 이미지가 없습니다.");
        }
    }
}