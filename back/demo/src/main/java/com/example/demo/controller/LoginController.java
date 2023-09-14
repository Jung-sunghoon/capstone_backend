package com.example.demo.controller;

import com.example.demo.dao.LoginDAO;
import com.example.demo.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginDAO loginDAO;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginDTO user = loginDAO.Login(loginDTO);

        if (user == null) {
            return ResponseEntity.status(401).body("아이디 또는 비밀번호가 잘못되었습니다.");
        }

        return ResponseEntity.ok("로그인 성공");
    }
}
