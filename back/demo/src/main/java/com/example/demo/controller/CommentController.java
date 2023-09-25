package com.example.demo.controller;

import com.example.demo.dao.CommentDAO;
import com.example.demo.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*")
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

    @GetMapping("/comments/{projectId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByProjectId(@PathVariable int projectId) {
        List<CommentDTO> comments = commentDAO.getCommentsByProjectId(projectId);

        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(comments);
    }

}

