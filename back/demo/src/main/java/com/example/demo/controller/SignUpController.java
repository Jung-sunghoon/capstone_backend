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
        signUpDAO.SignUpData(users);
        return ResponseEntity.ok("회원 가입 성공");
    }
}