package com.example.demo.controller;

import com.example.demo.dao.ItInfoDeleteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ItInfoDeleteController {

    @Autowired
    private ItInfoDeleteDAO itInfoDeleteDAO;

    @DeleteMapping("/delete_it_info/{itInfoId}")
    public ResponseEntity<String> deleteItInfo(@PathVariable int itInfoId) {

        itInfoDeleteDAO.deleteItInfo(itInfoId);

        return ResponseEntity.ok("IT 정보 글 삭제 완료");
    }
}
