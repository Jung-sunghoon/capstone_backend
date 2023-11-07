package com.example.demo.controller;

import com.example.demo.dao.SignUpDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class NickNameDuplicateCheack {
    @Autowired
    private SignUpDAO signUpDAO;


    @GetMapping("/nick_name_duplicate_check")
    public ResponseEntity<String> checkNickName(@RequestParam String nickName) {
        if (signUpDAO.checkNickName(nickName) > 0) {
            return ResponseEntity.status(402).body("이미 사용 중인 닉네임입니다.");
        }
        return ResponseEntity.ok("사용 가능합니다.");
    }
}
