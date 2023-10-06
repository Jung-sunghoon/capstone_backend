package com.example.demo.dao;

import com.example.demo.dto.LikesDTO;

public interface LikesDAO {
    int addLike(LikesDTO likeDTO);  // 좋아요 추가
    int removeLike(LikesDTO likeDTO);  // 좋아요 제거
    Boolean isLiked(LikesDTO likeDTO);  // 이미 좋아요 했는지 확인
    void increaseProjectLikes(LikesDTO likeDTO);
    void decreaseProjectLikes(LikesDTO likeDTO);
    void increasePointLikes(String projectGenerationUserId);
    void decreasePointLikes(String projectGenerationUserId);
}
