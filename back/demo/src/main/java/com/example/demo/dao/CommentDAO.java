package com.example.demo.dao;

import com.example.demo.dto.CommentDTO;
import java.util.List;

public interface CommentDAO {
    void saveComment(CommentDTO comment);
    List<CommentDTO> getCommentsByProjectId(int projectId);
    void increasePointComment(String userId);
    void decreasePointComment(String userId);
    void updateComment(CommentDTO comment);
    void deleteComment(int commentId);
}
