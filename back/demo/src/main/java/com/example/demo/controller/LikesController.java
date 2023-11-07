package com.example.demo.controller;

import com.example.demo.dao.LikesDAO;
import com.example.demo.dto.LikesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LikesController {
    @Autowired
    private LikesDAO likesDAO;

    @GetMapping("/check_like")
    public ResponseEntity<?> checkLike(@RequestParam String userId, @RequestParam int projectId) {
        LikesDTO likeDTO = new LikesDTO();
        likeDTO.setUserId(userId);
        likeDTO.setProjectId(projectId);
        boolean isLiked = likesDAO.isLiked(likeDTO);
        if (isLiked) {
            return new ResponseEntity<>("already liked this project", HttpStatus.OK);  // User already liked the project
        } else {
            return new ResponseEntity<>("hasn't liked this project", HttpStatus.OK); // User hasn't liked the project
        }
    }

    @PostMapping("/toggle_like")
    public ResponseEntity<?> toggleLike(@RequestParam String userId, @RequestParam int projectId, @RequestParam String projectGenerationUserId) {
        LikesDTO likeDTO = new LikesDTO();
        likeDTO.setUserId(userId);
        likeDTO.setProjectId(projectId);
        boolean isLiked = likesDAO.isLiked(likeDTO);

        if (isLiked) {
            likesDAO.removeLike(likeDTO); //db에서 삭제
            likesDAO.decreaseProjectLikes(likeDTO);  //좋아요 1감소
            likesDAO.decreasePointLikes(projectGenerationUserId);  //포인트 1감소
            return new ResponseEntity<>("Like removed", HttpStatus.OK);
        } else {
            likesDAO.addLike(likeDTO);
            likesDAO.increaseProjectLikes(likeDTO);
            likesDAO.increasePointLikes(projectGenerationUserId);
            return new ResponseEntity<>("Like added", HttpStatus.OK);
        }
    }
}
