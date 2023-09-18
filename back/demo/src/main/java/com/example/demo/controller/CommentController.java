package com.example.demo.controller;

import com.example.demo.dao.CommentDAO;
import com.example.demo.dto.CommentDTO;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentDAO commentDAO;

    @PostMapping("/comments")
    public ResponseEntity<String> saveComment(@RequestBody CommentDTO comment) {

        //현재 시간 추가
        long systemTime = System.currentTimeMillis();
        // 출력 형태를 위한 formmater
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        // format에 맞게 출력하기 위한 문자열 변환
        String dTime = formatter.format(systemTime);


        comment.setCreatedAt(dTime);

        commentDAO.saveComment(comment);
        return ResponseEntity.ok("댓글 저장 완료");
    }
}
