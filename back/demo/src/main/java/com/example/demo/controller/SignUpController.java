package com.example.demo.controller;

import com.example.demo.dao.SignUpDAO;
import com.example.demo.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SignUpController {

    @Autowired
    private SignUpDAO signUpDAO;


    @PostMapping("/sign_up")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDTO users) {
        System.out.println("Received request body: " + users.toString());
        if (signUpDAO.checkUserId(users.getUserId()) > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 사용 중인 아이디입니다.");
        }
        signUpDAO.SignUpData(users);
        return ResponseEntity.ok("회원 가입 성공");
    }
}