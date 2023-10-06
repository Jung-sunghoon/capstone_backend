package com.example.demo.controller;


import com.example.demo.dao.SignUpDAO;
import com.example.demo.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class IdDuplicateCheck {

    @Autowired
    private SignUpDAO signUpDAO;


    @GetMapping("/IdDuplicateCheck")
    public ResponseEntity<String> checkID(@RequestParam String id) {
        if (signUpDAO.checkUserId(id) > 0) {
            return ResponseEntity.status(402).body("이미 사용 중인 아이디입니다.");
        }
        return ResponseEntity.ok("사용 가능합니다.");
    }
}
