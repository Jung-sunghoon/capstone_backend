package com.example.demo.controller;

import com.example.demo.dao.UserInformationDAO;
import com.example.demo.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserInformationController {
    @Autowired
    private UserInformationDAO userInformationDAO;
    
    //id의 해단하는 유저의 정보를 가져옴
    @GetMapping("/user_information")
    public ResponseEntity<?> UserInformation(@RequestParam String userId) {
        SignUpDTO user = userInformationDAO.UserData(userId);
        if (user == null) {
            return new ResponseEntity<>("회원 정보가 없습니다", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //가져온 아이디 정보를 이융해 사용자가 입력한 정보를 업데이트
    @PostMapping("/user_information_update")
    public ResponseEntity<?> UserInformationUpdate(@RequestBody SignUpDTO user) {
        try {
            userInformationDAO.UpdateUserData(user);
            return new ResponseEntity<>("회원 정보가 업데이트 되었습니다", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("회원 정보 업데이트에 실패했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
