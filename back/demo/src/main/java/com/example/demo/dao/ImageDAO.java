package com.example.demo.dao;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ImageDAO {
    // 이미지 저장과 관련된 메소드 추가
    void saveImage(String imagePath);
}
