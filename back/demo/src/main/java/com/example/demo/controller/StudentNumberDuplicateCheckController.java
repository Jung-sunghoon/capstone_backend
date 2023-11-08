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
public class StudentNumberDuplicateCheckController {

    @Autowired
    private SignUpDAO signUpDAO;


    @GetMapping("/studentNumber_duplicate_check")
    public ResponseEntity<String> checkStudentNumber(@RequestParam int studentNumber) {
        //자리수 체크
        if((int)(Math.log10(studentNumber)+1) != 9  ){
            return ResponseEntity.status(402).body("학번을 확인해 주세요");
        }
        if (signUpDAO.checkStudentNumber(studentNumber) > 0) {
            return ResponseEntity.status(402).body("이미 사용중인 학번입니다.");
        }
        return ResponseEntity.ok("사용 가능합니다.");
    }
}
